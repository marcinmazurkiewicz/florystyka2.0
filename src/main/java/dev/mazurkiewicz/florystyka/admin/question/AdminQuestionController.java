package dev.mazurkiewicz.florystyka.admin.question;

import dev.mazurkiewicz.florystyka.question.QuestionResponse;
import dev.mazurkiewicz.florystyka.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v3/admin/questions")
@Secured({"ROLE_ADMIN"})
@RequiredArgsConstructor
public class AdminQuestionController {

    private final QuestionService questionService;

    @GetMapping
    public List<QuestionResponse> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}
