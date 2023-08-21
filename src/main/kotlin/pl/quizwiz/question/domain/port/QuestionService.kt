package pl.quizwiz.question.domain.port

import pl.quizwiz.question.domain.model.Question
import pl.quizwiz.question.domain.model.QuestionId
import pl.quizwiz.quiz.domain.port.QuizTemplateService

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
