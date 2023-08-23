package pl.quizwiz.template.domain.port

import pl.quizwiz.template.domain.model.QuizTemplate
import pl.quizwiz.template.domain.model.TemplateId

interface QuizTemplateRepository {

    fun saveTemplate(template: QuizTemplate)

    fun findTemplateById(templateId: TemplateId): QuizTemplate
}