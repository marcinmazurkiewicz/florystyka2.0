package io.mazurkiewicz.quizer.quiz.infrastructure.db

import io.mazurkiewicz.quizer.quiz.domain.model.*
import io.mazurkiewicz.quizer.quiz.infrastructure.QuizTemplateNotFoundException
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import java.time.Instant
import java.util.*

@SpringBootTest
class QuizTemplateDbRepositoryTest(
    @Autowired private val mongoRepository: QuizTemplateMongoRepository,
    @Autowired private val quizTemplateDbRepository: QuizTemplateDbRepository
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
            TemplateDrawSettings(DrawType.SHUFFLE, numberOfQuestionsToDraw),
            TemplatePassThreshold(defaultThreshold),
            TemplateId(templateId)
        )

        //when
        quizTemplateDbRepository.saveTemplate(template)

        //then
        val savedTemplate = mongoRepository.findByIdOrNull(templateId)
        assertThat(savedTemplate).isNotNull
        assertThat(savedTemplate?.name).isEqualTo(templateName)
        assertThat(savedTemplate?.author).isEqualTo(authorId)
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
            listOf(),
            Instant.now()
        )
        mongoRepository.insert(entity)

        //when
        val savedTemplate = quizTemplateDbRepository.findTemplateById(TemplateId(templateId))

        //then
        assertThat(savedTemplate.name.value).isEqualTo(templateName)
        assertThat(savedTemplate.author.authorId).isEqualTo(authorId)
        assertThat(savedTemplate.accessType).isEqualTo(TemplateAccessType.PUBLIC)
        assertThat(savedTemplate.defaultDrawSettings.type).isEqualTo(DrawType.SHUFFLE)
        assertThat(savedTemplate.defaultDrawSettings.questionNumber).isEqualTo(numberOfQuestionsToDraw)
        assertThat(savedTemplate.templatePassThreshold.percentage).isEqualTo(defaultThreshold)
    }

    @Test
    fun `should throw exception when quiz template with id does not exist`() {
        //when
        val block: () -> Unit = {  quizTemplateDbRepository.findTemplateById(TemplateId(UUID.randomUUID())) }

        //then
        assertThrows<QuizTemplateNotFoundException>(block)
    }
}