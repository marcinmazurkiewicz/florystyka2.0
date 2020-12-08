package dev.mazurkiewicz.florystyka.question;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
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

    @GetMapping("/test/pdf")
    public ResponseEntity<ByteArrayResource> generatePdf() {
        String filename = "r26.pdf";
        ByteArrayResource resource = new ByteArrayResource(service.getPdfTest());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition
                        .builder("attachment")
                        .filename(filename, StandardCharsets.UTF_8)
                        .build()
        );
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(resource.contentLength());
        return new ResponseEntity<>(resource,
                headers,
                HttpStatus.OK);
    }
}

