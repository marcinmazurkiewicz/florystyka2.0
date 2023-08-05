package io.mazurkiewicz.quizer.quiz.infrastructure

import io.mazurkiewicz.quizer.quiz.domain.model.*
import io.mazurkiewicz.quizer.quiz.domain.port.QuizTemplateRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class QuizTemplateDbRepository(private val mongoRepository: QuizTemplateMongoRepository) : QuizTemplateRepository {
    override fun saveTemplate(template: QuizTemplate) {
        val entity = TemplateMongoEntity(
            template.templateId.id,
            template.name.value,
            template.author.authorId,
            template.accessType,
            prepareEntityDefaultDrawSettings(template.defaultDrawSettings),
            template.templatePassThreshold.percentage
        )
        mongoRepository.insert(entity)
    }

    private fun prepareEntityDefaultDrawSettings(drawSettings: TemplateDrawSettings): DefaultDrawSettings {
        return if(drawSettings.type == DrawType.SHUFFLE) DefaultDrawSettings.enabled(drawSettings.questionNumber)
        else DefaultDrawSettings.disabled()
    }

    override fun findTemplateById(templateId: TemplateId): QuizTemplate {
        val templateEntity = mongoRepository.findByIdOrNull(templateId.id) ?: throw QuizTemplateNotFoundException(templateId.id)
        return QuizTemplate(
            TemplateName(templateEntity.name),
            TemplateAuthor(templateEntity.author),
            templateEntity.accessType,
            prepareDomainDefaultDrawSettings(templateEntity.defaultDrawSettings),
            TemplatePassThreshold(templateEntity.defaultPassPercentageThreshold),
            TemplateId(templateId.id)
        )
    }

    private fun prepareDomainDefaultDrawSettings(drawSettings: DefaultDrawSettings): TemplateDrawSettings {
        val drawType = if(drawSettings.drawEnabled) DrawType.SHUFFLE else DrawType.NONE
        return TemplateDrawSettings(drawType, drawSettings.numberOfQuestionsToDraw ?: 0)
    }
}