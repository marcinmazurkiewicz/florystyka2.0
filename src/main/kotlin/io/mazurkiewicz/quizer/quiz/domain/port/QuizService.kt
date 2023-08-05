package io.mazurkiewicz.quizer.quiz.domain.port

import io.mazurkiewicz.quizer.question.domain.port.QuestionNumber
import io.mazurkiewicz.quizer.question.domain.port.QuestionRepository
import io.mazurkiewicz.quizer.quiz.domain.model.QuizInstance
import io.mazurkiewicz.quizer.quiz.domain.model.QuizDuration
import io.mazurkiewicz.quizer.quiz.domain.model.QuizId
import io.mazurkiewicz.quizer.quiz.domain.model.QuizPassThreshold
import java.util.*

class QuizService(private val questionRepository: QuestionRepository) {

    fun generateQuiz(
        questionNumber: QuestionNumber,
        quizDuration: QuizDuration,
        passThreshold: QuizPassThreshold
    ): QuizInstance {
        val questions = questionRepository.findRandomQuestions(questionNumber)
        return QuizInstance(UUID.randomUUID(), questions, quizDuration, passThreshold)
    }

    fun recreateQuiz(quizId: QuizId): QuizInstance {
        val quizParams = quizId.decodeParams()
        val questions = questionRepository.findQuestions(quizParams.questionIds)
        return QuizInstance(UUID.randomUUID(), questions, quizParams.duration, quizParams.passThreshold, quizId)
    }
}