package io.mazurkiewicz.quizer.quiz.domain.port

import io.mazurkiewicz.quizer.quiz.domain.model.*

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
}