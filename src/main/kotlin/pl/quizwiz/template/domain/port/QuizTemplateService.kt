package pl.quizwiz.template.domain.port

import pl.quizwiz.question.domain.model.QuestionAuthor
import pl.quizwiz.question.domain.model.QuestionTemplate
import pl.quizwiz.template.domain.model.*
import pl.quizwiz.template.infrastructure.IllegalTemplateAccessException

class QuizTemplateService(private val quizTemplateRepository: QuizTemplateRepository) {

    fun createTemplate(
        name: TemplateName,
        author: TemplateAuthor,
        accessType: TemplateAccessType,
        defaultDrawSettings: TemplateDrawSettings,
        defaultPassPercentageThreshold: TemplatePassThreshold
    ): TemplateId {
        val quizTemplate = QuizTemplate(name, author, accessType, defaultDrawSettings, defaultPassPercentageThreshold)
        quizTemplateRepository.saveTemplate(quizTemplate)
        return quizTemplate.templateId
    }

    fun findTemplate(templateId: TemplateId): QuizTemplate {
        return quizTemplateRepository.findTemplateById(templateId)
    }

    fun validateAccessToTemplate(questionTemplate: QuestionTemplate, author: QuestionAuthor) {
        val template = findTemplate(TemplateId(questionTemplate.id))
        if (template.author.id != author.id) {
            throw IllegalTemplateAccessException(author.id, template.author.id)
        }
    }
}