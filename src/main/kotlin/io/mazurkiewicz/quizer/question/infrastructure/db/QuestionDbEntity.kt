package io.mazurkiewicz.quizer.question.infrastructure.db

import io.mazurkiewicz.quizer.question.domain.model.*
import jakarta.persistence.*
import java.util.*

@Entity(name = "questions")
class QuestionDbEntity(
    @Column(nullable = false, unique = true)
    val questionPublicId: UUID,
    @Column(nullable = false)
    val content: String,
    @Column(name = "answer_a", nullable = false)
    val answerA: String,
    @Column(name = "answer_b", nullable = false)
    val answerB: String,
    @Column(name = "answer_c", nullable = false)
    val answerC: String,
    @Column(name = "answer_d", nullable = false)
    val answerD: String,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val correct: AnswerType,
    val img: String? = null,
    val comment: String? = null,
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_id_generator")
    @SequenceGenerator(name = "question_id_generator", sequenceName = "questions_id_seq", allocationSize = 1)
    var id: Int? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuestionDbEntity

        if (questionPublicId != other.questionPublicId) return false
        if (content != other.content) return false
        if (answerA != other.answerA) return false
        if (answerB != other.answerB) return false
        if (answerC != other.answerC) return false
        if (answerD != other.answerD) return false
        if (correct != other.correct) return false
        if (img != other.img) return false
        return comment == other.comment
    }

    override fun hashCode(): Int {
        var result = questionPublicId.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + answerA.hashCode()
        result = 31 * result + answerB.hashCode()
        result = 31 * result + answerC.hashCode()
        result = 31 * result + answerD.hashCode()
        result = 31 * result + correct.hashCode()
        result = 31 * result + (img?.hashCode() ?: 0)
        result = 31 * result + (comment?.hashCode() ?: 0)
        return result
    }

    fun toDomain(): Question {
        return Question(
            QuestionId(questionPublicId),
            QuestionContent(content),
            listOf(
                Answer(AnswerType.A, AnswerContent(answerA), AnswerStatus.of(correct == AnswerType.A)),
                Answer(AnswerType.B, AnswerContent(answerB), AnswerStatus.of(correct == AnswerType.B)),
                Answer(AnswerType.C, AnswerContent(answerC), AnswerStatus.of(correct == AnswerType.C)),
                Answer(AnswerType.D, AnswerContent(answerD), AnswerStatus.of(correct == AnswerType.D))
            ),
            if(img != null) QuestionImage(img!!) else null,
            if(comment != null) QuestionComment(comment!!) else null
        )
    }
}