package pl.quizwiz.question.application

import org.springframework.stereotype.Service
import pl.quizwiz.question.domain.model.*
import pl.quizwiz.question.domain.port.QuestionService
import java.util.*

@Service
class ApiQuestionService(private val questionService: QuestionService) {

    fun getQuestionById(id: UUID): QuestionResponse {
        val questionId = QuestionId(id)
        val question = questionService.getQuestion(questionId)
        return QuestionResponse(question)
    }

    fun saveQuestion(templateId: UUID, content: String, answers: List<QuestionAnswer>, authorId: UUID): UUID {
        val question = Question(
            QuestionTemplate(templateId),
            QuestionAuthor(authorId),
            QuestionContent(content),
            mapToAnswers(answers)
        )
        questionService.saveQuestion(question)
        return question.id.value
    }

    private fun mapToAnswers(answers: List<QuestionAnswer>) =
        answers.map {
            Answer(
                it.type,
                AnswerContent(it.content),
                if (it.isCorrect) AnswerStatus.CORRECT else AnswerStatus.INCORRECT
            )
        }
}