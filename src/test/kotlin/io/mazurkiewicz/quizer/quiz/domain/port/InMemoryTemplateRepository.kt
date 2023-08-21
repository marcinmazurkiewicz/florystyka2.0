package io.mazurkiewicz.quizer.quiz.domain.port

import io.mazurkiewicz.quizer.quiz.domain.model.QuizTemplate
import io.mazurkiewicz.quizer.quiz.domain.model.TemplateId
import io.mazurkiewicz.quizer.quiz.infrastructure.QuizTemplateNotFoundException
import java.util.*

class InMemoryTemplateRepository : QuizTemplateRepository {

    private val entities: MutableMap<UUID, QuizTemplate> = mutableMapOf()
    override fun saveTemplate(template: QuizTemplate) {
        entities[template.templateId.id] = template
    }

    override fun findTemplateById(templateId: TemplateId): QuizTemplate {
        return entities[templateId.id] ?: throw QuizTemplateNotFoundException(templateId.id)
    }
}
