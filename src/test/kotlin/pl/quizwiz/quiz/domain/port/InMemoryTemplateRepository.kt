package pl.quizwiz.quiz.domain.port

import pl.quizwiz.quiz.domain.model.QuizTemplate
import pl.quizwiz.quiz.domain.model.TemplateId
import pl.quizwiz.quiz.infrastructure.QuizTemplateNotFoundException
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
