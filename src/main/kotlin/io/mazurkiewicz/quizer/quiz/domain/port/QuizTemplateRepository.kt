package io.mazurkiewicz.quizer.quiz.domain.port

import io.mazurkiewicz.quizer.quiz.domain.model.QuizTemplate
import io.mazurkiewicz.quizer.quiz.domain.model.TemplateId

interface QuizTemplateRepository {

    fun saveTemplate(template: QuizTemplate)

    fun findTemplateById(templateId: TemplateId): QuizTemplate
}