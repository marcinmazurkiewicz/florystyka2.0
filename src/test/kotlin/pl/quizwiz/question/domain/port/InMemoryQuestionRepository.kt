package pl.quizwiz.question.domain.port

import pl.quizwiz.question.domain.model.Question
import pl.quizwiz.question.domain.model.QuestionId
import pl.quizwiz.question.infrastructure.QuestionNotFoundException
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