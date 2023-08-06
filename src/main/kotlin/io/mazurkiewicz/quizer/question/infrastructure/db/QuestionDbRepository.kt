package io.mazurkiewicz.quizer.question.infrastructure.db

import io.mazurkiewicz.quizer.question.domain.model.Question
import io.mazurkiewicz.quizer.question.domain.model.QuestionId
import io.mazurkiewicz.quizer.question.domain.port.QuestionNumber
import io.mazurkiewicz.quizer.question.domain.port.QuestionRepository
import org.springframework.stereotype.Repository

@Repository
class QuestionDbRepository() : QuestionRepository {
    override fun findQuestionById(id: QuestionId): Question {
        TODO("Not yet implemented")
    }

    override fun findRandomQuestions(questionNumber: QuestionNumber): List<Question> {
        TODO("Not yet implemented")
    }

    override fun findRandomQuestion(): Question {
        return findRandomQuestions(QuestionNumber(1)).first()
    }

    override fun findQuestions(questionIds: List<QuestionId>): List<Question> {
        TODO("Not yet implemented")
    }

}
