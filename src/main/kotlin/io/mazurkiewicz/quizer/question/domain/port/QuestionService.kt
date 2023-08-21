package io.mazurkiewicz.quizer.question.domain.port

import io.mazurkiewicz.quizer.question.domain.model.Question
import io.mazurkiewicz.quizer.question.domain.model.QuestionId
import io.mazurkiewicz.quizer.quiz.domain.port.QuizTemplateService

class QuestionService(
    private val questionRepository: QuestionRepository,
    private val templateService: QuizTemplateService
) {

    fun getQuestion(questionId: QuestionId): Question {
        return questionRepository.findQuestionById(questionId)
    }

    fun saveQuestion(question: Question) {
        question.validateAnswers()
        templateService.validateAccessToTemplate(question.template, question.author)
        questionRepository.saveQuestion(question)
    }
}
