package io.mazurkiewicz.quizer.question.infrastructure.db

import io.mazurkiewicz.quizer.question.domain.model.*
import io.mazurkiewicz.quizer.question.domain.port.QuestionRepository
import io.mazurkiewicz.quizer.question.infrastructure.QuestionNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class QuestionDbRepository(
    private val questionMongoRepository: QuestionMongoRepository
) : QuestionRepository {

    override fun findQuestionById(id: QuestionId): Question {
        val questionEntity =
            questionMongoRepository.findByIdOrNull(id.value) ?: throw QuestionNotFoundException(id.value)
        return Question(
            QuestionTemplate(questionEntity.templateId),
            QuestionAuthor(questionEntity.authorId),
            QuestionContent(questionEntity.content),
            mapToAnswers(questionEntity),
            mapToQuestionImage(questionEntity.image),
            mapToQuestionComment(questionEntity.comment),
            QuestionId(questionEntity.id)
        )
    }

    override fun saveQuestion(question: Question) {
        val questionEntity = QuestionMongoEntity(
            question.id.value,
            question.template.id,
            question.author.id,
            question.content.value,
            mapToAnswerItems(question.answers),
            question.answers.find { answer -> answer.status == AnswerStatus.CORRECT }!!.type,
            mapToImagePath(question.image),
            mapToComment(question.comment)
        )
        questionMongoRepository.save(questionEntity)
    }

    private fun mapToAnswers(questionEntity: QuestionMongoEntity) = questionEntity.answers.map { answer ->
        Answer(
            answer.answerType,
            AnswerContent(answer.content),
            if (questionEntity.correct == answer.answerType) AnswerStatus.CORRECT else AnswerStatus.INCORRECT
        )
    }

    private fun mapToQuestionImage(imagePath: String?) =
        if (imagePath != null) QuestionImage(imagePath) else QuestionImage()

    private fun mapToQuestionComment(comment: String?) =
        if (comment != null) QuestionComment(comment) else QuestionComment()

    private fun mapToAnswerItems(answers: List<Answer>) = answers.map { AnswerItem(it.type, it.content.value) }

    private fun mapToImagePath(image: QuestionImage) = if (image.exist()) image.path else null

    private fun mapToComment(comment: QuestionComment) = if (comment.exist()) comment.value else null

}
