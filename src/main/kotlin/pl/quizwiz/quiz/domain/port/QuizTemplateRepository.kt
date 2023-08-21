package pl.quizwiz.quiz.domain.port

import pl.quizwiz.quiz.domain.model.QuizTemplate
import pl.quizwiz.quiz.domain.model.TemplateId

interface QuizTemplateRepository {

    fun saveTemplate(template: QuizTemplate)

    fun findTemplateById(templateId: TemplateId): QuizTemplate
}