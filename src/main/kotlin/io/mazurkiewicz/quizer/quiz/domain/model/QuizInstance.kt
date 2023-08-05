package io.mazurkiewicz.quizer.quiz.domain.model

import io.mazurkiewicz.quizer.question.domain.model.*
import java.util.*

class QuizInstance(
    val templateId: UUID,
    val questions: List<Question>,
    val duration: QuizDuration,
    val percentPassThreshold: QuizPassThreshold,
    val id: QuizId = QuizId.encode(questions.map { it.id.value }, duration.toSeconds(), percentPassThreshold.percent),
    val availablePoints: AvailablePoints = AvailablePoints(questions.size)
) {

    fun checkQuiz(quizSolution: QuizSolution): QuizResult {
        val questionSolutions = quizSolution.selectedAnswers
            .map { entry -> QuizQuestionSolution(questions.single { it.id == entry.key }, SelectedAnswer(entry.value)) }

        val achievedPoints = questionSolutions
            .map { it.question.checkAnswer(it.selectedAnswer) }
            .count { it.isCorrect() }

        val quizStatus =
            if (achievedPoints < percentPassThreshold.calculateMinimalPointsNumber(availablePoints)) QuizStatus.FAILED
            else QuizStatus.PASSED
        return QuizResult(Points(AchievedPoints(achievedPoints), availablePoints), quizStatus)
    }

    fun correctAnswers(): QuizCorrectAnswers {
        val correctAnswers = questions.associate { question -> question.id to question.correctAnswer() }
        return QuizCorrectAnswers(correctAnswers)
    }
}

data class QuizId(val value: String) {

    companion object {
        const val QUESTION_SPLITTER = "^"
        const val SPLITTER = "/"
        fun encode(questionIds: List<UUID>, duration: Int, passThreshold: Int): QuizId {
            val joinedQuestionIds = questionIds.joinToString(QUESTION_SPLITTER)
            val valueToEncode = "$duration$SPLITTER$passThreshold$SPLITTER$joinedQuestionIds"
            val encodedValue = Base64.getEncoder().encode(valueToEncode.toByteArray())
            return QuizId(String(encodedValue))
        }
    }

    fun decodeParams(): QuizParams {
        val decodedValue = String(Base64.getDecoder().decode(value.toByteArray()))
        val idParts = decodedValue.split(SPLITTER)
        val duration = idParts[0].toInt()
        val passThreshold = idParts[1].toInt()
        val questionIds = idParts[2].split(QUESTION_SPLITTER)
            .map { QuestionId(UUID.fromString(it)) }

        return QuizParams(QuizDuration.fromSeconds(duration), QuizPassThreshold(passThreshold), questionIds)
    }
}

data class QuizDuration(val hours: Int, val minutes: Int, val seconds: Int) {
    fun toSeconds(): Int {
        return seconds + (minutes * 60) + (hours * 3600)
    }

    companion object {
        fun fromSeconds(seconds: Int): QuizDuration {
            var secs = seconds
            val hours = seconds / 3600
            secs -= hours * 3600
            val minutes = secs / 60
            secs %= 60
            return QuizDuration(hours, minutes, secs)
        }
    }
}

data class QuizParams(
    val duration: QuizDuration,
    val passThreshold: QuizPassThreshold,
    val questionIds: List<QuestionId>
)

data class QuizPassThreshold(val percent: Int) {

    fun calculateMinimalPointsNumber(totalPoints: AvailablePoints) = totalPoints.value * percent / 100.0
}

data class AvailablePoints(val value: Int)

data class AchievedPoints(val value: Int)

data class QuizResult(val points: Points, val status: QuizStatus)

data class Points(val achievedPoints: AchievedPoints, val availablePoints: AvailablePoints)

data class QuizSolution(val selectedAnswers: Map<QuestionId, AnswerType>)

data class QuizQuestionSolution(val question: Question, val selectedAnswer: SelectedAnswer)

data class QuizCorrectAnswers(val correctAnswers: Map<QuestionId, Answer>)

enum class QuizStatus {
    PASSED, FAILED;
}