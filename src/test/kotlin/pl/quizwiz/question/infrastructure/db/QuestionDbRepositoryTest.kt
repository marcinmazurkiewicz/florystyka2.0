package pl.quizwiz.question.infrastructure.db

import org.apache.commons.lang3.RandomStringUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import pl.quizwiz.question.domain.model.*
import pl.quizwiz.question.infrastructure.QuestionNotFoundException
import java.util.*

@SpringBootTest
class QuestionDbRepositoryTest(
    @Autowired private val questionMongoRepository: QuestionMongoRepository,
    @Autowired private val questionDbRepository: QuestionDbRepository
) {

    @Test
    fun `should save new valid question to db`() {
        //given
        val answerA = RandomStringUtils.randomAlphabetic(16)
        val answerB = RandomStringUtils.randomAlphabetic(16)
        val answerC = RandomStringUtils.randomAlphabetic(16)
        val answerD = RandomStringUtils.randomAlphabetic(16)
        val question = Question(
            QuestionTemplate(UUID.randomUUID()),
            QuestionAuthor(UUID.randomUUID()),
            QuestionContent(RandomStringUtils.randomAlphabetic(32)),
            listOf(
                Answer(AnswerType.A, AnswerContent(answerA), AnswerStatus.INCORRECT),
                Answer(AnswerType.B, AnswerContent(answerB), AnswerStatus.CORRECT),
                Answer(AnswerType.C, AnswerContent(answerC), AnswerStatus.INCORRECT),
                Answer(AnswerType.D, AnswerContent(answerD), AnswerStatus.INCORRECT),
            ),
            QuestionImage(RandomStringUtils.randomAlphabetic(16))
        )

        //when
        questionDbRepository.saveQuestion(question)

        //then
        val savedQuestion = questionMongoRepository.findByIdOrNull(question.id.value)!!
        assertThat(savedQuestion.content).isEqualTo(question.content.value)
        assertThat(savedQuestion.correct).isEqualTo(AnswerType.B)
        assertThat(savedQuestion.image).isEqualTo(question.image.path)
        assertThat(savedQuestion.templateId).isEqualTo(question.template.id)
        assertThat(savedQuestion.authorId).isEqualTo(question.author.id)
        assertThat(savedQuestion.createdAt).isNotNull()
        assertThat(savedQuestion.modifiedAt).isNotNull()
        assertThat(savedQuestion.answers).containsExactly(
            AnswerItem(AnswerType.A, answerA),
            AnswerItem(AnswerType.B, answerB),
            AnswerItem(AnswerType.C, answerC),
            AnswerItem(AnswerType.D, answerD)
        )
    }

    @Test
    fun `should find question by id`() {
        //given
        val answerA = RandomStringUtils.randomAlphabetic(16)
        val answerB = RandomStringUtils.randomAlphabetic(16)
        val answerC = RandomStringUtils.randomAlphabetic(16)
        val answerD = RandomStringUtils.randomAlphabetic(16)
        val questionEntity = QuestionMongoEntity(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            RandomStringUtils.randomAlphabetic(32),
            listOf(
                AnswerItem(AnswerType.A, answerA),
                AnswerItem(AnswerType.B, answerB),
                AnswerItem(AnswerType.C, answerC),
                AnswerItem(AnswerType.D, answerD)

            ),
            AnswerType.A,
            RandomStringUtils.randomAlphabetic(16),
            RandomStringUtils.randomAlphabetic(16)
        )
        questionMongoRepository.save(questionEntity)

        //when
        val question = questionDbRepository.findQuestionById(QuestionId(questionEntity.id))

        //then
        assertThat(question.content.value).isEqualTo(questionEntity.content)
        assertThat(question.template.id).isEqualTo(questionEntity.templateId)
        assertThat(question.author.id).isEqualTo(questionEntity.authorId)
        assertThat(question.image.path).isEqualTo(questionEntity.image)
        assertThat(question.answers).containsExactly(
            Answer(AnswerType.A, AnswerContent(answerA), AnswerStatus.CORRECT),
            Answer(AnswerType.B, AnswerContent(answerB), AnswerStatus.INCORRECT),
            Answer(AnswerType.C, AnswerContent(answerC), AnswerStatus.INCORRECT),
            Answer(AnswerType.D, AnswerContent(answerD), AnswerStatus.INCORRECT)
        )
    }

    @Test
    fun `should throw exception when question with id does not exist`() {
        //given
        val questionId = QuestionId(UUID.randomUUID())

        //when
        val call: () -> Unit = { questionDbRepository.findQuestionById(questionId) }

        //then
        val error = assertThrows<QuestionNotFoundException>(call)
        assertThat(error.errorCode).isEqualTo("QW_1005")
    }
}