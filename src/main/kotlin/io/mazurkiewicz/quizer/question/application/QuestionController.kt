package io.mazurkiewicz.quizer.question.application

import io.mazurkiewicz.quizer.question.domain.model.*
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/questions")
class QuestionController(private val service: ApiQuestionService) {

    @GetMapping("/random")
    fun getRandomQuestion() = service.getRandomQuestion()

    @GetMapping("/{questionId}")
    fun getQuestionById(@PathVariable questionId: UUID) = service.getQuestionById(questionId)

    @PostMapping("/{questionId}/answer")
    fun checkAnswer(@PathVariable questionId: UUID, @Valid @RequestBody selectedAnswerRequest: SelectedAnswerRequest) =
        service.checkAnswer(questionId, selectedAnswerRequest.selectedAnswer)
}

data class QuestionResponse(
    val id: UUID,
    val content: String,
    val answers: List<AnswerResponse>,
    val image: String? = null
) {
    constructor(question: Question) : this(
        question.id.value,
        question.content.value,
        question.answers.map { AnswerResponse(it) },
        question.image?.path
    )
}

data class AnswerResponse(
    val type: AnswerType,
    val content: String
) {
    constructor(answer: Answer) : this(answer.type, answer.content.value)
}

data class SelectedAnswerRequest(
    @NotBlank @ValidAnswerType val selectedAnswer: String
)

data class AnswerStatusResponse(
    val questionId: UUID,
    val status: AnswerStatus,
    val correctAnswer: AnswerType
)