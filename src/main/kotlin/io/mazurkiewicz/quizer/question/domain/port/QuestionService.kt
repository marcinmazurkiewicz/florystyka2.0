package io.mazurkiewicz.quizer.question.domain.port

import io.mazurkiewicz.quizer.question.domain.model.AnswerResult
import io.mazurkiewicz.quizer.question.domain.model.Question
import io.mazurkiewicz.quizer.question.domain.model.QuestionId
import io.mazurkiewicz.quizer.question.domain.model.SelectedAnswer

class QuestionService(private val questionRepository: QuestionRepository) {

    fun getQuestion(questionId: QuestionId): Question {
        return questionRepository.findQuestionById(questionId)
    }

    fun getRandomQuestion(): Question {
        return questionRepository.findRandomQuestion()
    }

    fun checkAnswer(questionId: QuestionId, selectedAnswer: SelectedAnswer): AnswerResult {
        val question = questionRepository.findQuestionById(questionId)
        return question.checkAnswer(selectedAnswer)
    }
}
