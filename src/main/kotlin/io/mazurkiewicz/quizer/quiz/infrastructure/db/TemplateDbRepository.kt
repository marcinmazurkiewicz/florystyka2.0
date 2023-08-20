package io.mazurkiewicz.quizer.quiz.infrastructure.db

import io.mazurkiewicz.quizer.quiz.domain.model.*
import io.mazurkiewicz.quizer.quiz.domain.port.QuizTemplateRepository
import io.mazurkiewicz.quizer.quiz.infrastructure.QuizTemplateNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class TemplateDbRepository(private val mongoRepository: TemplateMongoRepository) : QuizTemplateRepository {
    override fun saveTemplate(template: QuizTemplate) {
        val entity = TemplateMongoEntity(
            template.templateId.id,
            template.name.value,
            template.author.id,
            template.accessType,
            prepareEntityDefaultDrawSettings(template.defaultDrawSettings),
            template.templatePassThreshold.percentage
        )
        mongoRepository.insert(entity)
    }

    override fun findTemplateById(templateId: TemplateId): QuizTemplate {
        val templateEntity =
            mongoRepository.findByIdOrNull(templateId.id) ?: throw QuizTemplateNotFoundException(templateId.id)
        return QuizTemplate(
            TemplateName(templateEntity.name),
            TemplateAuthor(templateEntity.authorId),
            templateEntity.accessType,
            prepareDomainDefaultDrawSettings(templateEntity.defaultDrawSettings),
            TemplatePassThreshold(templateEntity.defaultPassPercentageThreshold),
            TemplateId(templateId.id)
        )
    }

    private fun prepareEntityDefaultDrawSettings(drawSettings: TemplateDrawSettings): DefaultDrawSettings {
        return if (drawSettings.type == DrawType.DRAW) DefaultDrawSettings(true, drawSettings.questionNumber)
        else DefaultDrawSettings(false)
    }

    private fun prepareDomainDefaultDrawSettings(drawSettings: DefaultDrawSettings): TemplateDrawSettings {
        val drawType = if (drawSettings.drawEnabled) DrawType.DRAW else DrawType.NONE
        return TemplateDrawSettings(drawType, drawSettings.numberOfQuestionsToDraw ?: 0)
    }
}