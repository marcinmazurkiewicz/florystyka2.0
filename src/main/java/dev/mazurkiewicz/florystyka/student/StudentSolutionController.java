package dev.mazurkiewicz.florystyka.student;

import dev.mazurkiewicz.florystyka.solution.SolutionRequest;
import dev.mazurkiewicz.florystyka.solution.SolutionResponse;
import dev.mazurkiewicz.florystyka.solution.SolutionStatsResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v3/logged/solutions")
@Secured("ROLE_STUDENT")
public class StudentSolutionController {

    private final StudentSolutionService service;

    public StudentSolutionController(StudentSolutionService service) {
        this.service = service;
    }

    @GetMapping("/stats/single")
    public SolutionStatsResponse getSolutionsStatsForLoggedUser() {
        return service.getSingleSolutionsStatsForLoggedStudent();
    }

    @PostMapping("/single")
    public SolutionResponse checkAnswer(@Valid @RequestBody SolutionRequest solution) {
        return service.checkAndSaveSingle(solution);
    }
}

