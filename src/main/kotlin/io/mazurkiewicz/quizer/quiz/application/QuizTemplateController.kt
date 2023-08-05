package io.mazurkiewicz.quizer.quiz.application

import io.mazurkiewicz.quizer.quiz.domain.model.DrawType
import io.mazurkiewicz.quizer.quiz.domain.model.TemplateAccessType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/quizzes/templates")
class QuizTemplateController(private val apiService: ApiQuizTemplateService) {

    @PostMapping
    fun createNewTemplate(@RequestBody templateRequest: CreateQuizTemplateRequest): ResponseEntity<CreateQuizTemplateResponse> {
        val templateId = apiService.createNewQuizTemplate(
            templateRequest.name,
            templateRequest.accessType,
            templateRequest.shuffle,
            templateRequest.defaultPassPercentThreshold
         )
        return ResponseEntity.ok(CreateQuizTemplateResponse(templateId))
    }
}

data class CreateQuizTemplateRequest(
    val name: String,
    val accessType: TemplateAccessType,
    val shuffle: DefaultDrawParams,
    val defaultPassPercentThreshold: Int
)

data class DefaultDrawParams(val type: DrawType, val questionsNumber: Int = 0)

data class CreateQuizTemplateResponse(val quizId: UUID)

