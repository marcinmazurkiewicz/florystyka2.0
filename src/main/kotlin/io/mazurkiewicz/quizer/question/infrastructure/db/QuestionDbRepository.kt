package io.mazurkiewicz.quizer.question.infrastructure.db

import io.mazurkiewicz.quizer.question.domain.model.Question
import io.mazurkiewicz.quizer.question.domain.model.QuestionId
import io.mazurkiewicz.quizer.question.domain.port.QuestionNumber
import io.mazurkiewicz.quizer.question.domain.port.QuestionRepository
import io.mazurkiewicz.quizer.question.infrastructure.QuestionNotFoundException
import io.mazurkiewicz.quizer.question.infrastructure.QuestionNumberIsIncorrectException
import org.springframework.stereotype.Repository

@Repository
class QuestionDbRepository(private val jpaRepository: JpaQuestionRepository) : QuestionRepository {
    override fun findQuestionById(id: QuestionId): Question {
        val questionEntity = (jpaRepository.findByQuestionPublicId(id.value)
            ?: throw QuestionNotFoundException(id.value))
        return questionEntity.toDomain()
    }

    override fun findRandomQuestions(questionNumber: QuestionNumber): List<Question> {
        val questions = jpaRepository.findRandomQuestions(questionNumber.value)
            .map { it.toDomain() }
        if (questions.size != questionNumber.value) {
            throw QuestionNumberIsIncorrectException(questionNumber.value, questions.size)
        }
        return questions
    }

    override fun findRandomQuestion(): Question {
        return findRandomQuestions(QuestionNumber(1)).first()
    }

    override fun findQuestions(questionIds: List<QuestionId>): List<Question> {
        val ids = questionIds.map { it.value }.toSet()
        return jpaRepository.findAllByQuestionPublicIdIsIn(ids)
            .map { it.toDomain() }
    }
}

