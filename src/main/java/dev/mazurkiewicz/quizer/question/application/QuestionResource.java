package dev.mazurkiewicz.quizer.question.application;

import dev.mazurkiewicz.quizer.config.EndpointProperties;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(EndpointProperties.QUESTIONS_ENDPOINT_MAIN)
class QuestionResource {

    private final ApiQuestionService service;

    @GetMapping(EndpointProperties.QUESTIONS_ENDPOINT_RANDOM)
    QuestionResponse getRandomQuestion() {
        try {
            return service.getRandomQuestion();
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    QuestionResponse getQuestionById(@PathVariable("id") Integer id) {
        return service.getQuestionById(id);
    }

    @PostMapping(EndpointProperties.SOLUTIONS_ENDPOINT_SINGLE)
    public AnswerStatusResponse checkAnswer(@Valid @RequestBody SelectedAnswerRequest selectedAnswerRequest) {
        return service.checkAnswer(selectedAnswerRequest);
    }
}

