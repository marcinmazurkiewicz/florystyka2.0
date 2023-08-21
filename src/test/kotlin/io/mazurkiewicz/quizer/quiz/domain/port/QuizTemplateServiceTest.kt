package io.mazurkiewicz.quizer.quiz.domain.port

import io.mazurkiewicz.quizer.question.domain.model.*
import io.mazurkiewicz.quizer.quiz.domain.model.*
import io.mazurkiewicz.quizer.quiz.infrastructure.IllegalTemplateAccessException
import io.mazurkiewicz.quizer.quiz.infrastructure.QuizTemplateNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class QuizTemplateServiceTest {
    private val quizTemplateRepository = InMemoryTemplateRepository()
    private val quizTemplateService: QuizTemplateService = QuizTemplateService(quizTemplateRepository)

    @Test
    fun `should create new quiz and return id`() {
        val templateName = TemplateName("test template")
        val templateAuthor = TemplateAuthor(UUID.randomUUID())
        val accessType = TemplateAccessType.PUBLIC
        val drawSettings = TemplateDrawSettings(DrawType.NONE)
        val defaultPercentPassThreshold = TemplatePassThreshold(51)

        val templateId = quizTemplateService.createTemplate(
            templateName,
            templateAuthor,
            accessType,
            drawSettings,
            defaultPercentPassThreshold
        )

        assertThat(templateId).isNotNull
    }

    @Test
    fun `should find and return created template`() {
        val templateName = TemplateName("test template to found")
        val templateAuthor = TemplateAuthor(UUID.randomUUID())
        val accessType = TemplateAccessType.PUBLIC
        val drawSettings = TemplateDrawSettings(DrawType.NONE)
        val defaultPercentPassThreshold = TemplatePassThreshold(51)
        val templateId = quizTemplateService.createTemplate(
            templateName,
            templateAuthor,
            accessType,
            drawSettings,
            defaultPercentPassThreshold
        )
        val searchedTemplate = quizTemplateService.findTemplate(templateId)

        assertThat(searchedTemplate.name.value).isEqualTo(templateName.value)
        assertThat(searchedTemplate.author.id).isEqualTo(templateAuthor.id)
        assertThat(searchedTemplate.accessType).isEqualTo(accessType)
        assertThat(searchedTemplate.defaultDrawSettings.type).isEqualTo(drawSettings.type)
        assertThat(searchedTemplate.defaultDrawSettings.questionNumber).isEqualTo(drawSettings.questionNumber)
        assertThat(searchedTemplate.templatePassThreshold.percentage).isEqualTo(defaultPercentPassThreshold.percentage)
    }

    @Test
    fun `should validate that question author is the same as template author`() {
        //given
        val authorId = UUID.randomUUID()
        val templateId = quizTemplateService.createTemplate(
            TemplateName("test template"),
            TemplateAuthor(authorId),
            TemplateAccessType.PUBLIC,
            TemplateDrawSettings(DrawType.NONE),
            TemplatePassThreshold(5)
        )

        val question = Question(
            QuestionTemplate(templateId.id),
            QuestionAuthor(authorId),
            QuestionContent("Test question 1"),
            listOf(
                Answer(AnswerType.A, AnswerContent("answer a"), AnswerStatus.INCORRECT),
                Answer(AnswerType.B, AnswerContent("answer b"), AnswerStatus.INCORRECT),
                Answer(AnswerType.C, AnswerContent("answer c"), AnswerStatus.INCORRECT),
                Answer(AnswerType.D, AnswerContent("answer d"), AnswerStatus.CORRECT)
            )
        )

        //when
        quizTemplateService.validateAccessToTemplate(QuestionTemplate(templateId.id), question.author)

        //then
        assertThatNoException()
    }

    @Test
    fun `should throw exception when question author is not the same as template author`() {
        //given
        val authorId = UUID.randomUUID()
        val templateId = quizTemplateService.createTemplate(
            TemplateName("test template"),
            TemplateAuthor(authorId),
            TemplateAccessType.PUBLIC,
            TemplateDrawSettings(DrawType.NONE),
            TemplatePassThreshold(5)
        )

        val question = Question(
            QuestionTemplate(templateId.id),
            QuestionAuthor(UUID.randomUUID()),
            QuestionContent("Test question 1"),
            listOf(
                Answer(AnswerType.A, AnswerContent("answer a"), AnswerStatus.INCORRECT),
                Answer(AnswerType.B, AnswerContent("answer b"), AnswerStatus.INCORRECT),
                Answer(AnswerType.C, AnswerContent("answer c"), AnswerStatus.INCORRECT),
                Answer(AnswerType.D, AnswerContent("answer d"), AnswerStatus.CORRECT)
            )
        )

        //when
        val block: () -> Unit = { quizTemplateService.validateAccessToTemplate(QuestionTemplate(templateId.id), question.author) }

        //then
        assertThrows<IllegalTemplateAccessException>(block)
    }

    @Test
    fun `should throw exception when question is assigned to not existing template`() {
        //given
        val question = Question(
            QuestionTemplate(UUID.randomUUID()),
            QuestionAuthor(UUID.randomUUID()),
            QuestionContent("Test question 1"),
            listOf(
                Answer(AnswerType.A, AnswerContent("answer a"), AnswerStatus.INCORRECT),
                Answer(AnswerType.B, AnswerContent("answer b"), AnswerStatus.INCORRECT),
                Answer(AnswerType.C, AnswerContent("answer c"), AnswerStatus.INCORRECT),
                Answer(AnswerType.D, AnswerContent("answer d"), AnswerStatus.CORRECT)
            )
        )

        //when
        val block: () -> Unit = { quizTemplateService.validateAccessToTemplate(question.template, question.author) }

        //then
        assertThrows<QuizTemplateNotFoundException>(block)
    }
}