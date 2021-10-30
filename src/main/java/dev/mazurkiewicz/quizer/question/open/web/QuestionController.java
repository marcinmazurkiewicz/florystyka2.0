package dev.mazurkiewicz.quizer.question.open.web;

import dev.mazurkiewicz.quizer.exception.PdfRenderException;
import dev.mazurkiewicz.quizer.question.open.domain.QuestionService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@RestController
@RequestMapping("api/v3/questions")
public class QuestionController {

    private final QuestionService service;
    private final MeterRegistry registry;

    public QuestionController(QuestionService service, MeterRegistry registry) {
        this.service = service;
        this.registry = registry;
    }

    @GetMapping("/random")
    public QuestionResponse getRandomQuestion() {
        try {
            return service.getRandomQuestion();
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public QuestionResponse getQuestionById(@PathVariable("id") Integer id) {
        return service.getQuestionById(id);
    }

    @GetMapping("/test")
    public TestResponse getQuestionToTest() {
        try {
            return service.getQuestionsToTest();
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
        }
    }

    @GetMapping("/info")
    public QuestionNumberResponse getQuestionNumber() {
        return service.countQuestions();
    }

    @GetMapping("/test/pdf")
    public ResponseEntity<ByteArrayResource> generatePdf() {
        String filename = "r26.pdf";
        byte[] pdfBytes = new byte[0];
        Timer.Sample timerSample = Timer.start();
        try {
            pdfBytes = service.getPdfTest();
        } catch (PdfRenderException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } finally {
            boolean successfulGeneration = pdfBytes.length > 0;
            Timer timer = buildTimer(filename, successfulGeneration);
            timerSample.stop(timer);
        }
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
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

    private Timer buildTimer(String filename, Boolean success) {
        return Timer.builder("generate_file")
                .tag("type", "pdf")
                .tag("name", filename)
                .tag("success",success.toString())
                .publishPercentileHistogram(true)
                .minimumExpectedValue(Duration.ofMillis(1L))
                .maximumExpectedValue(Duration.ofSeconds(30L))
                .register(registry);
    }
}

