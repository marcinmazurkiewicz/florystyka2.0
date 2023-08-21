package io.mazurkiewicz.quizer.question.application

import io.mazurkiewicz.quizer.question.domain.model.*
import io.mazurkiewicz.quizer.question.domain.model.Answer
import io.mazurkiewicz.quizer.question.domain.port.QuestionService
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*


class ApiQuestionServiceTest {
    private val questionService = mockk<QuestionService>()
    private val apiQuestionService = ApiQuestionService(questionService)

    @Test
    fun `should return QuestionResponse for valid question id`() {
        //given
        val questionId = UUID.randomUUID()
        val image = "/path/to/image.jpg"
        val content = "test question"
        every { questionService.getQuestion(any()) } returns Question(
            QuestionTemplate(UUID.randomUUID()),
            QuestionAuthor(UUID.randomUUID()),
            QuestionContent(content),
            listOf(
                Answer(AnswerType.A, AnswerContent("answer A"), AnswerStatus.INCORRECT),
                Answer(AnswerType.B, AnswerContent("answer B"), AnswerStatus.CORRECT),
                Answer(AnswerType.C, AnswerContent("answer C"), AnswerStatus.INCORRECT),
                Answer(AnswerType.D, AnswerContent("answer D"), AnswerStatus.INCORRECT),
            ),
            QuestionImage(image),
            QuestionComment(),
            QuestionId(questionId)
        )

        //when
        val question = apiQuestionService.getQuestionById(questionId)

        //then
        verify(exactly = 1) { questionService.getQuestion(any()) }
        assertThat(question.id).isEqualTo(questionId)
        assertThat(question.content).isEqualTo(content)
        assertThat(question.answers).containsAll(
            listOf(
                AnswerResponse(AnswerType.A, "answer A"),
                AnswerResponse(AnswerType.B, "answer B"),
                AnswerResponse(AnswerType.C, "answer C"),
                AnswerResponse(AnswerType.D, "answer D")
            )
        )
        assertThat(question.image).isEqualTo(image)
    }

    @Test
    fun `should add valid question`() {
        //given
        val templateId = UUID.randomUUID()
        val authorId = UUID.randomUUID()
        every { questionService.saveQuestion(any()) } just Runs

        //when
        val questionId = apiQuestionService.saveQuestion(
            templateId,
            "Test question",
            listOf(
                QuestionAnswer(AnswerType.A, "answer A", false),
                QuestionAnswer(AnswerType.B, "answer B", false),
                QuestionAnswer(AnswerType.C, "answer C", true),
                QuestionAnswer(AnswerType.D, "answer D", false)
            ),
            authorId
        )

        //then
        assertThat(questionId).isNotNull()
        verify(exactly = 1) { questionService.saveQuestion(any()) }
    }
}