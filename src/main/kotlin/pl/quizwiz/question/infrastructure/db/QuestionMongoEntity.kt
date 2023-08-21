package pl.quizwiz.question.infrastructure.db

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import pl.quizwiz.question.domain.model.AnswerType
import java.time.Instant
import java.util.*

@Document(collection = "questions")

class QuestionMongoEntity(
    @Id var id: UUID,
    var templateId: UUID,
    var authorId: UUID,
    var content: String,
    var answers: List<AnswerItem>,
    val correct: AnswerType,
    val image: String?,
    val comment: String?,
    val createdAt: Instant = Instant.now(),
    val modifiedAt: Instant = Instant.now()
)

data class AnswerItem(val answerType: AnswerType, val content: String)