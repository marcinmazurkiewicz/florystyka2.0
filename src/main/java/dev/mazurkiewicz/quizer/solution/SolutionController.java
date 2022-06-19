package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.config.EndpointProperties;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(EndpointProperties.SOLUTIONS_ENDPOINT_MAIN)
@RequiredArgsConstructor
public class SolutionController {

    private final SolutionService service;

    @PostMapping(EndpointProperties.SOLUTIONS_ENDPOINT_SINGLE)
    public AnswerResponse checkAnswer(@Valid @RequestBody AnswerRequest solution) {
        return service.checkSingle(solution);
    }

    @PostMapping(EndpointProperties.SOLUTIONS_ENDPOINT_EXAM)
    public AnswerResponse checkTest(@Valid @RequestBody List<AnswerRequest> solutions) {
        return service.checkTest(solutions);
    }

}

