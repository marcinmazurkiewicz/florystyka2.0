package io.mazurkiewicz.quizer.question.application

import io.mazurkiewicz.quizer.question.domain.model.AnswerType
import io.mazurkiewicz.quizer.question.domain.model.QuestionId
import io.mazurkiewicz.quizer.question.domain.model.SelectedAnswer
import io.mazurkiewicz.quizer.question.domain.port.QuestionService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ApiQuestionService(private val questionService: QuestionService) {

    fun getRandomQuestion(): QuestionResponse {
        val randomQuestion = questionService.getRandomQuestion()
        return QuestionResponse(randomQuestion)
    }

    fun getQuestionById(id: UUID): QuestionResponse {
        val questionId = QuestionId(id)
        val question = questionService.getQuestion(questionId)
        return QuestionResponse(question)
    }

    fun checkAnswer(requestQuestionId: UUID, requestSelectedAnswer: String): AnswerStatusResponse {
        val questionId = QuestionId(requestQuestionId)
        val selectedAnswer = SelectedAnswer(AnswerType.of(requestSelectedAnswer))
        val answerResult = questionService.checkAnswer(questionId, selectedAnswer)
        return AnswerStatusResponse(questionId.value, answerResult.status, answerResult.correctAnswer)
    }
}