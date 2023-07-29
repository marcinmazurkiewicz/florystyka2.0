package io.mazurkiewicz.quizer.question.domain.port

import io.mazurkiewicz.quizer.question.domain.model.Question
import io.mazurkiewicz.quizer.question.domain.model.QuestionId

interface QuestionRepository {

    fun findQuestionById(id: QuestionId): Question

    fun findRandomQuestions(questionNumber: QuestionNumber): List<Question>

    fun findRandomQuestion(): Question

    fun findQuestions(questionIds: List<QuestionId>): List<Question>

}

data class QuestionNumber(val value: Int)
