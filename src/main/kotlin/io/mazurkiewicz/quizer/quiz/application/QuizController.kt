package io.mazurkiewicz.quizer.quiz.application

import io.mazurkiewicz.quizer.question.application.QuestionResponse
import io.mazurkiewicz.quizer.question.domain.model.AnswerType
import io.mazurkiewicz.quizer.quiz.domain.model.Points
import io.mazurkiewicz.quizer.quiz.domain.model.QuizStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/quizzes")
class QuizController(private val service: ApiQuizService) {

    @GetMapping("/generate")
    fun generateQuiz() = service.generateQuiz()

    @PostMapping("/check")
    fun checkQuiz(@RequestBody quizSolutionRequest: QuizSolutionRequest) =
        service.checkQuiz(quizSolutionRequest.quizId, quizSolutionRequest.selectedAnswers)

}

data class QuizResponse(val quizId: String, val quizTimer: QuizTimer, val questions: List<QuestionResponse>)

data class QuizSolutionRequest(val quizId: String, val selectedAnswers: Map<UUID, String>)

data class QuizSolutionResponse(val points: Points, val status: QuizStatus, val correctAnswers: Map<UUID, AnswerType>)