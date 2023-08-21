package io.mazurkiewicz.quizer.question.domain.port

import io.mazurkiewicz.quizer.question.domain.model.Question
import io.mazurkiewicz.quizer.question.domain.model.QuestionId
import io.mazurkiewicz.quizer.question.infrastructure.QuestionNotFoundException
import java.util.*

class InMemoryQuestionRepository : QuestionRepository {

    private val entities = mutableMapOf<UUID, Question>()
    override fun findQuestionById(id: QuestionId): Question {
        return entities[id.value] ?: throw QuestionNotFoundException(id.value)
    }

    override fun saveQuestion(question: Question) {
        entities[question.id.value] = question
    }
}