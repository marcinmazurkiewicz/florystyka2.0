package dev.mazurkiewicz.quizer.solution;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v3/solutions")
@RequiredArgsConstructor
public class SolutionController {

    private final SolutionService service;

    @PostMapping("/single")
    public AnswerResponse checkAnswer(@Valid @RequestBody AnswerRequest solution) {
        return service.checkSingle(solution);
    }

    @PostMapping("/test")
    public AnswerResponse checkTest(@Valid @RequestBody List<AnswerRequest> solutions) {
        return service.checkTest(solutions);
    }

}

