package io.mazurkiewicz.quizer.question.domain.port

import io.mazurkiewicz.quizer.question.domain.model.Question
import io.mazurkiewicz.quizer.question.domain.model.QuestionId

interface QuestionRepository {

    fun findQuestionById(id: QuestionId): Question

    fun saveQuestion(question: Question)

}