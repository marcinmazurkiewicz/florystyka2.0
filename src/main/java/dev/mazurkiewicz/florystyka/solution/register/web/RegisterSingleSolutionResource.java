package dev.mazurkiewicz.florystyka.solution.register.web;

import dev.mazurkiewicz.florystyka.solution.open.web.CorrectAnswerCheckRequest;
import dev.mazurkiewicz.florystyka.solution.open.web.SolutionResponse;
import dev.mazurkiewicz.florystyka.solution.open.web.SolutionStatsResponse;
import dev.mazurkiewicz.florystyka.solution.register.domain.RegisterSingleSolutionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v3/logged/solutions/single")
@Secured("ROLE_STUDENT")
@AllArgsConstructor
public class RegisterSingleSolutionResource {

    private final RegisterSingleSolutionService singleSolutionService;

    @PostMapping
    public SolutionResponse checkAnswer(@Valid @RequestBody CorrectAnswerCheckRequest solution) {
        return singleSolutionService.checkAndSaveSingle(solution);
    }

    @GetMapping("/stats")
    public SolutionStatsResponse getSolutionsStatsForLoggedUser() {
        return singleSolutionService.getSingleSolutionsStatsForLoggedStudent();
    }
}

