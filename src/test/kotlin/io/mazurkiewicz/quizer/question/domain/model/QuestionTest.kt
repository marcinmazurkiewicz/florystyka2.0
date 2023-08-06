package io.mazurkiewicz.quizer.question.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class QuestionTest {

    @Test
    fun `should return correct when selected answer is correct`() {
        //given
        val testedQuestion = prepareAnswer()
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
        val testedQuestion = prepareAnswer()
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
        val testedQuestion = prepareAnswer()
        val selectedAnswer = SelectedAnswer(AnswerType.EMPTY)

        //when
        val answerResult = testedQuestion.checkAnswer(selectedAnswer)

        //then
        assertThat(answerResult.status).isEqualTo(AnswerStatus.INCORRECT)
        assertThat(answerResult.correctAnswer).isEqualTo(AnswerType.C)
    }

    private fun prepareAnswer(): Question {
        val questionId = QuestionId(UUID.randomUUID())
        val questionContent = QuestionContent("Test question #1")
        val questionImage = QuestionImage("/path/to/img.jpg")
        val answers = listOf(
            Answer(AnswerType.A, AnswerContent("question #1 answer A"), AnswerStatus.INCORRECT),
            Answer(AnswerType.B, AnswerContent("question #1 answer B"), AnswerStatus.INCORRECT),
            Answer(AnswerType.C, AnswerContent("question #1 answer C"), AnswerStatus.CORRECT),
            Answer(AnswerType.D, AnswerContent("question #1 answer D"), AnswerStatus.INCORRECT)
        )
        return Question(questionId, questionContent, answers, questionImage, null)
    }
}