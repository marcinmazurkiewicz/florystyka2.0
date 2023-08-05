package io.mazurkiewicz.quizer.question.domain.model

data class Answer(val type: AnswerType, val content: AnswerContent, val status: AnswerStatus)

data class AnswerContent(val value: String)

data class AnswerResult(val status: AnswerStatus, val correctAnswer: AnswerType) {
    fun isCorrect(): Boolean {
        return status == AnswerStatus.CORRECT
    }
}

data class SelectedAnswer(val value: AnswerType)

enum class AnswerStatus {
    CORRECT, INCORRECT;

    companion object {
        fun of(isCorrect: Boolean) = if (isCorrect) CORRECT else INCORRECT
    }
}

enum class AnswerType {
    A, B, C, D, EMPTY;

    companion object {
        fun of(answer: String): AnswerType {
            if (answer.isBlank()) return EMPTY

            val answerType = values().find { it.name == answer.uppercase() }
            return answerType
                ?: throw IllegalArgumentException("Answer outside the cafeteria!")
        }
    }
}