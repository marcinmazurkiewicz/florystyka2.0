package io.mazurkiewicz.quizer.quiz.infrastructure

import io.mazurkiewicz.quizer.question.domain.model.AnswerType
import io.mazurkiewicz.quizer.quiz.domain.model.TemplateAccessType
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document(collection = "templates")
class TemplateMongoEntity(
    @Id var id: UUID,
    var name: String,
    var author: UUID,
    var accessType: TemplateAccessType,
    var defaultDrawSettings: DefaultDrawSettings,
    var defaultPassPercentageThreshold: Int,
    var questions: List<Question> = listOf(),
    var createdAt: Instant = Instant.now()
) {

}

data class DefaultDrawSettings(val drawEnabled: Boolean, val numberOfQuestionsToDraw: Int? = null) {
    companion object {
        fun enabled(numberOfQuestionsToDraw: Int) = DefaultDrawSettings(true, numberOfQuestionsToDraw)
        fun disabled() = DefaultDrawSettings(false)
    }
}

data class Question(
    val content: String,
    val answerA: String,
    val answerB: String,
    val answerC: String,
    val answerD: String,
    val correct: AnswerType,
    val questionPublicId: UUID = UUID.randomUUID(),
    val image: String?,
    val comment: String?
)