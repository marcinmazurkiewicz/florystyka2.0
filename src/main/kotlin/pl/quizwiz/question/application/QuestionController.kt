package pl.quizwiz.question.application

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.quizwiz.auth.user.UserRepresentation
import pl.quizwiz.question.domain.model.Answer
import pl.quizwiz.question.domain.model.AnswerType
import pl.quizwiz.question.domain.model.Question
import java.util.*

@RestController
@RequestMapping("/api/questions")
class QuestionController(private val service: ApiQuestionService) {

    @GetMapping("/{questionId}")
    fun getQuestionById(@PathVariable questionId: UUID): ResponseEntity<QuestionResponse> {
        val question = service.getQuestionById(questionId)
        return ResponseEntity.ok(question)
    }

    @PostMapping
    fun saveQuestion(
        @AuthenticationPrincipal user: UserRepresentation,
        @Valid @RequestBody newQuestionRequest: NewQuestionRequest
    ): ResponseEntity<NewQuestionResponse> {
        val questionId = service.saveQuestion(
            newQuestionRequest.templateId,
            newQuestionRequest.content,
            newQuestionRequest.answers,
            user.id
        )
        return ResponseEntity.ok(NewQuestionResponse(questionId))
    }
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
        if (question.image.exist()) question.image.path else null
    )
}

data class AnswerResponse(
    val type: AnswerType,
    val content: String
) {
    constructor(answer: Answer) : this(answer.type, answer.content.value)
}

data class NewQuestionRequest(
    val templateId: UUID,
    val content: String,
    val answers: List<QuestionAnswer>
)

data class NewQuestionResponse(val questionId: UUID)

data class QuestionAnswer(val type: AnswerType, val content: String, val isCorrect: Boolean)
