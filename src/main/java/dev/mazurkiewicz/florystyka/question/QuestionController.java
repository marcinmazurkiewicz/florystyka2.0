package dev.mazurkiewicz.florystyka.question;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v3/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/random")
    public QuestionResponse getRandomQuestion() {
        return service.getRandomQuestion();
    }

    @GetMapping("/{id}")
    public QuestionResponse getQuestionById(@PathVariable("id") Integer id) {
        return service.getQuestionById(id);
    }

    @GetMapping("/test")
    public List<QuestionResponse> getQuestionToTest() {
        return service.getQuestionsToTest();
    }

    @GetMapping("/info")
    public QuestionNumberResponse getQuestionNumber() {
        return service.countQuestions();
    }
}

