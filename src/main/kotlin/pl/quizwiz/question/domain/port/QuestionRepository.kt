package pl.quizwiz.question.domain.port

import pl.quizwiz.question.domain.model.Question
import pl.quizwiz.question.domain.model.QuestionId

interface QuestionRepository {

    fun findQuestionById(id: QuestionId): Question

    fun saveQuestion(question: Question)

}