package pl.quizwiz.template.domain.model

import java.util.*

class QuizTemplate(
    val name: TemplateName,
    val author: TemplateAuthor,
    val accessType: TemplateAccessType,
    val defaultDrawSettings: TemplateDrawSettings,
    val templatePassThreshold: TemplatePassThreshold,
    val templateId: TemplateId = TemplateId(UUID.randomUUID())
)

data class TemplateId(val id: UUID)

data class TemplateName(val value: String)

data class TemplateAuthor(val id: UUID)

data class TemplateDrawSettings(val type: DrawType, val questionNumber: Int = 0)

data class TemplatePassThreshold(val percentage: Int)


