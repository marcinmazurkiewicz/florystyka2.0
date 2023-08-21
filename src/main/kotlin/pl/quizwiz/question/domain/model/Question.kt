package pl.quizwiz.question.domain.model

import pl.quizwiz.question.infrastructure.QuestionInvalidAnswersStateException
import pl.quizwiz.question.infrastructure.QuestionWithoutCorrectAnswerException
import java.util.*

data class Question(
    val template: QuestionTemplate,
    val author: QuestionAuthor,
    val content: QuestionContent,
    val answers: List<Answer>,
    val image: QuestionImage = QuestionImage(),
    val comment: QuestionComment = QuestionComment(),
    val id: QuestionId = QuestionId(UUID.randomUUID())
) {
    fun checkAnswer(selectedAnswer: SelectedAnswer): AnswerResult {
        val answerFromCafeteria = answers.find { it.type == selectedAnswer.value }
        val selectedAnswerStatus = answerFromCafeteria?.status ?: AnswerStatus.INCORRECT
        return AnswerResult(selectedAnswerStatus, findCorrectAnswer().type)
    }

    private fun findCorrectAnswer(): Answer {
        return answers.find { it.status == AnswerStatus.CORRECT }
            ?: throw QuestionWithoutCorrectAnswerException(id.value)

    }

    fun validateAnswers() {
        AnswerType.values()
            .forEach { if (it != AnswerType.EMPTY) checkIfAnswerTypeExist(it) }
        findCorrectAnswer()
    }

    private fun checkIfAnswerTypeExist(type: AnswerType) {
        val answerTypeOccurrences = answers.filter { it.type == type }.size
        if (answerTypeOccurrences != 1) {
            throw QuestionInvalidAnswersStateException(type, answerTypeOccurrences)
        }
    }
}

data class QuestionContent(val value: String)

data class QuestionId(val value: UUID)

data class QuestionComment(val value: String = "") {
    fun exist() = value.isNotBlank()
}

data class QuestionImage(val path: String = "") {
    fun exist() = path.isNotBlank()
}

data class QuestionAuthor(val id: UUID)

data class QuestionTemplate(val id: UUID)
