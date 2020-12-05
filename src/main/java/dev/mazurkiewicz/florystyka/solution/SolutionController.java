package dev.mazurkiewicz.florystyka.solution;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v3/solutions")
public class SolutionController {

    private final SolutionService service;

    public SolutionController(SolutionService service) {
        this.service = service;
    }

    @PostMapping("/single")
    public SolutionResponse checkAnswer(@RequestBody SolutionRequest solution) {
        return service.checkSingleAnswer(solution);
    }

    @PostMapping("/test")
    public TestSolutionResponse checkTest(@RequestBody List<SolutionRequest> solutions) {
        return service.checkTest(solutions);
    }

}

