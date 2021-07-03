package dev.mazurkiewicz.florystyka.student;

import dev.mazurkiewicz.florystyka.solution.SolutionRequest;
import dev.mazurkiewicz.florystyka.solution.SolutionResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v3/logged/solutions")
@Secured("ROLE_STUDENT")
public class StudentSolutionController {

    private final StudentSolutionService service;

    public StudentSolutionController(StudentSolutionService service) {
        this.service = service;
    }

    @PostMapping("/single")
    public SolutionResponse checkAnswer(@Valid @RequestBody SolutionRequest solution) {
        return service.checkAndSaveSingle(solution);
    }
}

