package io.mazurkiewicz.quizer.question.domain.model

import io.mazurkiewicz.quizer.question.infrastructure.QuestionInvalidAnswersStateException
import io.mazurkiewicz.quizer.question.infrastructure.QuestionWithoutCorrectAnswerException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.util.*

class QuestionTest {

    @Test
    fun `should return correct when selected answer is correct`() {
        //given
        val testedQuestion = prepareQuestion()
        val selectedAnswer = SelectedAnswer(AnswerType.C)

        //when
        val answerResult = testedQuestion.checkAnswer(selectedAnswer)

        //then
        assertThat(answerResult.status).isEqualTo(AnswerStatus.CORRECT)
        assertThat(answerResult.correctAnswer).isEqualTo(AnswerType.C)
    }

    @Test
    fun `should return incorrect when selected answer is incorrect`() {
        //given
        val testedQuestion = prepareQuestion()
        val selectedAnswer = SelectedAnswer(AnswerType.B)

        //when
        val answerResult = testedQuestion.checkAnswer(selectedAnswer)

        //then
        assertThat(answerResult.status).isEqualTo(AnswerStatus.INCORRECT)
        assertThat(answerResult.correctAnswer).isEqualTo(AnswerType.C)
    }

    @Test
    fun `should return incorrect when selected answer is empty`() {
        //given
        val testedQuestion = prepareQuestion()
        val selectedAnswer = SelectedAnswer(AnswerType.EMPTY)

        //when
        val answerResult = testedQuestion.checkAnswer(selectedAnswer)

        //then
        assertThat(answerResult.status).isEqualTo(AnswerStatus.INCORRECT)
        assertThat(answerResult.correctAnswer).isEqualTo(AnswerType.C)
    }

    @Test
    fun `should not throwing exception when answers are valid`() {
        //given
        val question = Question(
            QuestionTemplate(UUID.randomUUID()),
            QuestionAuthor(UUID.randomUUID()),
            QuestionContent("Test question"),
            listOf(
                Answer(AnswerType.A, AnswerContent("answer A"), AnswerStatus.CORRECT),
                Answer(AnswerType.B, AnswerContent("answer B"), AnswerStatus.INCORRECT),
                Answer(AnswerType.C, AnswerContent("answer C"), AnswerStatus.INCORRECT),
                Answer(AnswerType.D, AnswerContent("answer D"), AnswerStatus.INCORRECT)
            )
        )

        //expect
        assertDoesNotThrow { question.validateAnswers() }
    }

    @Test
    fun `should throw exception when answer type occurs 2 times`() {
        //given
        val question = Question(
            QuestionTemplate(UUID.randomUUID()),
            QuestionAuthor(UUID.randomUUID()),
            QuestionContent("Test question"),
            listOf(
                Answer(AnswerType.A, AnswerContent("answer A"), AnswerStatus.CORRECT),
                Answer(AnswerType.A, AnswerContent("answer A"), AnswerStatus.INCORRECT),
                Answer(AnswerType.C, AnswerContent("answer C"), AnswerStatus.INCORRECT),
                Answer(AnswerType.D, AnswerContent("answer D"), AnswerStatus.INCORRECT)
            )
        )

        //expect
        assertThrows<QuestionInvalidAnswersStateException> { question.validateAnswers() }
    }

    @Test
    fun `should throw exception when all answers are incorrect`() {
        //given
        val question = Question(
            QuestionTemplate(UUID.randomUUID()),
            QuestionAuthor(UUID.randomUUID()),
            QuestionContent("Test question"),
            listOf(
                Answer(AnswerType.A, AnswerContent("answer A"), AnswerStatus.INCORRECT),
                Answer(AnswerType.B, AnswerContent("answer B"), AnswerStatus.INCORRECT),
                Answer(AnswerType.C, AnswerContent("answer C"), AnswerStatus.INCORRECT),
                Answer(AnswerType.D, AnswerContent("answer D"), AnswerStatus.INCORRECT)
            )
        )

        //expect
        assertThrows<QuestionWithoutCorrectAnswerException> { question.validateAnswers() }
    }

    private fun prepareQuestion(): Question {
        val answers = listOf(
            Answer(AnswerType.A, AnswerContent("question #1 answer A"), AnswerStatus.INCORRECT),
            Answer(AnswerType.B, AnswerContent("question #1 answer B"), AnswerStatus.INCORRECT),
            Answer(AnswerType.C, AnswerContent("question #1 answer C"), AnswerStatus.CORRECT),
            Answer(AnswerType.D, AnswerContent("question #1 answer D"), AnswerStatus.INCORRECT)
        )
        return Question(
            QuestionTemplate(UUID.randomUUID()),
            QuestionAuthor(UUID.randomUUID()),
            QuestionContent("Test question #1"),
            answers,
            QuestionImage("/path/to/img.jpg"),
            QuestionComment(),
            QuestionId(UUID.randomUUID())
        )
    }
}