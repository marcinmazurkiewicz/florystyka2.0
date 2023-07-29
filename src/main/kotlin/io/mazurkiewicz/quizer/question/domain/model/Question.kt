package io.mazurkiewicz.quizer.question.domain.model

import java.util.UUID

data class Question(
    val id: QuestionId,
    val content: QuestionContent,
    val answers: List<Answer>,
    val image: QuestionImage? = null,
    val comment: QuestionComment? = null
) {
    fun checkAnswer(selectedAnswer: SelectedAnswer): AnswerResult {
        val answerFromCafeteria = answers.find { it.type == selectedAnswer.value }
        val selectedAnswerStatus = answerFromCafeteria?.status ?: AnswerStatus.INCORRECT
        return AnswerResult(selectedAnswerStatus, getCorrectAnswer().type)
    }

    private fun getCorrectAnswer(): Answer {
        return answers.find { it.status == AnswerStatus.CORRECT }
            ?: throw IllegalStateException("Question ${id.value} without correct answer!")

    }
}

data class QuestionContent(val value: String)

data class QuestionId(val value: UUID)

data class QuestionComment(val value: String)

data class QuestionImage(val path: String)
