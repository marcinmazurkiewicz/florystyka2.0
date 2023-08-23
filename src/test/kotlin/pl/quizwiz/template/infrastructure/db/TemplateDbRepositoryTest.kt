package pl.quizwiz.template.infrastructure.db

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import pl.quizwiz.template.domain.model.*
import pl.quizwiz.template.infrastructure.QuizTemplateNotFoundException
import java.time.Instant
import java.util.*

@SpringBootTest
class TemplateDbRepositoryTest(
    @Autowired private val templateMongoRepository: TemplateMongoRepository,
    @Autowired private val templateDbRepository: TemplateDbRepository
) {

    @Test
    fun `should save new quiz template`() {
        //given
        val templateId = UUID.randomUUID()
        val templateName = "Test template 1"
        val authorId = UUID.randomUUID()
        val numberOfQuestionsToDraw = 5
        val defaultThreshold = 51

        val template = QuizTemplate(
            TemplateName(templateName),
            TemplateAuthor(authorId),
            TemplateAccessType.PUBLIC,
            TemplateDrawSettings(DrawType.DRAW, numberOfQuestionsToDraw),
            TemplatePassThreshold(defaultThreshold),
            TemplateId(templateId)
        )

        //when
        templateDbRepository.saveTemplate(template)

        //then
        val savedTemplate = templateMongoRepository.findByIdOrNull(templateId)
        assertThat(savedTemplate).isNotNull
        assertThat(savedTemplate?.name).isEqualTo(templateName)
        assertThat(savedTemplate?.authorId).isEqualTo(authorId)
        assertThat(savedTemplate?.accessType).isEqualTo(TemplateAccessType.PUBLIC)
        assertThat(savedTemplate?.defaultDrawSettings?.drawEnabled).isTrue()
        assertThat(savedTemplate?.defaultDrawSettings?.numberOfQuestionsToDraw).isEqualTo(numberOfQuestionsToDraw)
        assertThat(savedTemplate?.defaultPassPercentageThreshold).isEqualTo(defaultThreshold)
    }

    @Test
    fun `should find existing quiz template by id`() {
        //given
        val templateId = UUID.randomUUID()
        val templateName = "Test template 2"
        val authorId = UUID.randomUUID()
        val numberOfQuestionsToDraw = 5
        val defaultThreshold = 51

        val entity = TemplateMongoEntity(
            templateId,
            templateName,
            authorId,
            TemplateAccessType.PUBLIC,
            DefaultDrawSettings(true, numberOfQuestionsToDraw),
            defaultThreshold,
            Instant.now()
        )
        templateMongoRepository.insert(entity)

        //when
        val savedTemplate = templateDbRepository.findTemplateById(TemplateId(templateId))

        //then
        assertThat(savedTemplate.name.value).isEqualTo(templateName)
        assertThat(savedTemplate.author.id).isEqualTo(authorId)
        assertThat(savedTemplate.accessType).isEqualTo(TemplateAccessType.PUBLIC)
        assertThat(savedTemplate.defaultDrawSettings.type).isEqualTo(DrawType.DRAW)
        assertThat(savedTemplate.defaultDrawSettings.questionNumber).isEqualTo(numberOfQuestionsToDraw)
        assertThat(savedTemplate.templatePassThreshold.percentage).isEqualTo(defaultThreshold)
    }

    @Test
    fun `should throw exception when quiz template with id does not exist`() {
        //when
        val block: () -> Unit = { templateDbRepository.findTemplateById(TemplateId(UUID.randomUUID())) }

        //then
        assertThrows<QuizTemplateNotFoundException>(block)
    }
}