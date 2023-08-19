package io.mazurkiewicz.quizer.quiz.application

import io.mazurkiewicz.quizer.auth.user.UserRepresentation
import io.mazurkiewicz.quizer.quiz.domain.model.DrawType
import io.mazurkiewicz.quizer.quiz.domain.model.TemplateAccessType
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/quizzes/templates")
class QuizTemplateController(private val apiService: ApiQuizTemplateService) {

    @PostMapping
    fun createNewTemplate(
        @AuthenticationPrincipal user: UserRepresentation,
        @RequestBody @Valid templateRequest: CreateQuizTemplateRequest
    ): ResponseEntity<CreateQuizTemplateResponse> {
        val templateId = apiService.createNewQuizTemplate(
            templateRequest.name,
            templateRequest.accessType,
            templateRequest.drawParams,
            templateRequest.defaultPassPercentThreshold,
            user.id
        )
        return ResponseEntity.ok(CreateQuizTemplateResponse(templateId))
    }
}

data class CreateQuizTemplateRequest(
    @field:NotBlank val name: String,
    val accessType: TemplateAccessType,
    val drawParams: DefaultDrawParams,
    val defaultPassPercentThreshold: Int
)

data class DefaultDrawParams(val type: DrawType, val questionsNumber: Int = 0)

data class CreateQuizTemplateResponse(val templateId: UUID)

