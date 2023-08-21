package io.mazurkiewicz.quizer.question.domain.port

import io.mazurkiewicz.quizer.question.domain.model.*
import io.mazurkiewicz.quizer.question.domain.model.Answer
import io.mazurkiewicz.quizer.question.infrastructure.QuestionInvalidAnswersStateException
import io.mazurkiewicz.quizer.question.infrastructure.QuestionWithoutCorrectAnswerException
import io.mazurkiewicz.quizer.quiz.domain.port.QuizTemplateService
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class QuestionServiceTest {
    private val questionRepository = InMemoryQuestionRepository()
    private val templateService = mockk<QuizTemplateService>()
    private val questionService = QuestionService(questionRepository, templateService)

    @Test
    fun `should validate and save new question`() {
        //given
        val question = Question(
            QuestionTemplate(UUID.randomUUID()),
            QuestionAuthor(UUID.randomUUID()),
            QuestionContent("test question 1"),
            listOf(
                Answer(AnswerType.A, AnswerContent("answer A"), AnswerStatus.CORRECT),
                Answer(AnswerType.B, AnswerContent("answer B"), AnswerStatus.INCORRECT),
                Answer(AnswerType.C, AnswerContent("answer C"), AnswerStatus.INCORRECT),
                Answer(AnswerType.D, AnswerContent("answer D"), AnswerStatus.INCORRECT)
            )
        )
        every { templateService.validateAccessToTemplate(any(), any()) } just Runs

        //when
        questionService.saveQuestion(question)

        //then
        val searchedQuestion = questionRepository.findQuestionById(question.id)
        assertThat(searchedQuestion).isEqualTo(question)
        verify(exactly = 1) { templateService.validateAccessToTemplate(any(), any()) }
    }

    @Test
    fun `should throw exception when question does not have correct question`() {
        //given
        val question = Question(
            QuestionTemplate(UUID.randomUUID()),
            QuestionAuthor(UUID.randomUUID()),
            QuestionContent("test question 1"),
            listOf(
                Answer(AnswerType.A, AnswerContent("answer A"), AnswerStatus.INCORRECT),
                Answer(AnswerType.B, AnswerContent("answer B"), AnswerStatus.INCORRECT),
                Answer(AnswerType.C, AnswerContent("answer C"), AnswerStatus.INCORRECT),
                Answer(AnswerType.D, AnswerContent("answer D"), AnswerStatus.INCORRECT)
            )
        )

        //when
        val call: () -> Unit = { questionService.saveQuestion(question) }

        //then
        val error = assertThrows<QuestionWithoutCorrectAnswerException>(call)
        assertThat(error.errorCode).isEqualTo("QW_1003")
        verify(exactly = 0) { templateService.validateAccessToTemplate(any(), any()) }
    }

    @Test
    fun `should throw exception when question does not have valid answers`() {
        //given
        val question = Question(
            QuestionTemplate(UUID.randomUUID()),
            QuestionAuthor(UUID.randomUUID()),
            QuestionContent("test question 1"),
            listOf(
                Answer(AnswerType.B, AnswerContent("answer B"), AnswerStatus.INCORRECT),
                Answer(AnswerType.B, AnswerContent("answer B"), AnswerStatus.INCORRECT),
                Answer(AnswerType.C, AnswerContent("answer C"), AnswerStatus.INCORRECT),
                Answer(AnswerType.D, AnswerContent("answer D"), AnswerStatus.INCORRECT)
            )
        )

        //when
        val call: () -> Unit = { questionService.saveQuestion(question) }

        //then
        val error = assertThrows<QuestionInvalidAnswersStateException>(call)
        assertThat(error.errorCode).isEqualTo("QW_1004")
        verify(exactly = 0) { templateService.validateAccessToTemplate(any(), any()) }
    }
}