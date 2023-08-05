package io.mazurkiewicz.quizer.quiz.application

import io.mazurkiewicz.quizer.config.QuizerProperties
import io.mazurkiewicz.quizer.question.application.QuestionResponse
import io.mazurkiewicz.quizer.question.domain.model.AnswerType
import io.mazurkiewicz.quizer.question.domain.model.QuestionId
import io.mazurkiewicz.quizer.question.domain.port.QuestionNumber
import io.mazurkiewicz.quizer.quiz.domain.model.QuizDuration
import io.mazurkiewicz.quizer.quiz.domain.model.QuizId
import io.mazurkiewicz.quizer.quiz.domain.model.QuizPassThreshold
import io.mazurkiewicz.quizer.quiz.domain.model.QuizSolution
import io.mazurkiewicz.quizer.quiz.domain.port.QuizService
import org.springframework.stereotype.Component
import java.util.*

@Component
class ApiQuizService(
    private val quizService: QuizService,
    private val quizerProperties: QuizerProperties
) {
    fun generateQuiz(): QuizResponse {
        val quiz = quizService.generateQuiz(
            QuestionNumber(quizerProperties.quizQuestionNumber),
            QuizDuration.fromSeconds(quizerProperties.quizTimeInSeconds),
            QuizPassThreshold(quizerProperties.quizPassThreshold)
        )
        val questions = quiz.questions
            .map { QuestionResponse(it) }

        return QuizResponse(quiz.id.value, QuizTimer.of(quiz.duration), questions)
    }

    fun checkQuiz(quizId: String, selectedAnswers: Map<UUID, String>): QuizSolutionResponse {
        val quiz = quizService.recreateQuiz(QuizId(quizId))
        val solution =
            selectedAnswers.entries.associate { entry -> QuestionId(entry.key) to AnswerType.of(entry.value) }
        val quizResult = quiz.checkQuiz(QuizSolution(solution))
        val correctAnswers = quiz.correctAnswers()
            .correctAnswers
            .entries
            .associate { it.key.value to it.value.type }
        return QuizSolutionResponse(quizResult.points, quizResult.status, correctAnswers)
    }

}


data class QuizTimer(val hours: Int, val minutes: Int, val seconds: Int) {
    companion object {
        fun of(quizDuration: QuizDuration): QuizTimer {
            return QuizTimer(quizDuration.hours, quizDuration.minutes, quizDuration.seconds)
        }
    }
}
