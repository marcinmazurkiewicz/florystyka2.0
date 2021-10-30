package dev.mazurkiewicz.quizer.solution.open.web;

import dev.mazurkiewicz.quizer.solution.open.domain.AnswerCheckingService;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v3/solutions")
public class CorrectAnswerController {

    private final AnswerCheckingService service;

    public CorrectAnswerController(AnswerCheckingService service) {
        this.service = service;
    }

    @PostMapping("/single")
    public SolutionResponse checkAnswer(@Valid @RequestBody CorrectAnswerCheckRequest solution) {
        return service.checkSingleAnswer(solution);
    }

    @PostMapping("/test")
    public SolutionResponse checkTest(@Valid @RequestBody List<CorrectAnswerCheckRequest> solutions) {
        try {
            return service.checkTest(solutions);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}

