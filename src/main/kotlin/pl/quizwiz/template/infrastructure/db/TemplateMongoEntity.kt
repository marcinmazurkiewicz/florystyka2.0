package pl.quizwiz.template.infrastructure.db

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import pl.quizwiz.template.domain.model.TemplateAccessType
import java.time.Instant
import java.util.*

@Document(collection = "templates")
class TemplateMongoEntity(
    @Id var id: UUID,
    var name: String,
    var authorId: UUID,
    var accessType: TemplateAccessType,
    var defaultDrawSettings: DefaultDrawSettings,
    var defaultPassPercentageThreshold: Int,
    var createdAt: Instant = Instant.now(),
    var modifiedAt: Instant = Instant.now()
)

data class DefaultDrawSettings(val drawEnabled: Boolean, val numberOfQuestionsToDraw: Int? = null)
