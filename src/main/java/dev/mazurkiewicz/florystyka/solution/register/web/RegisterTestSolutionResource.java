package dev.mazurkiewicz.florystyka.solution.register.web;

import dev.mazurkiewicz.florystyka.solution.open.web.CorrectAnswerCheckRequest;
import dev.mazurkiewicz.florystyka.solution.open.web.SolutionResponse;
import dev.mazurkiewicz.florystyka.solution.open.web.SolutionStatsResponse;
import dev.mazurkiewicz.florystyka.solution.register.domain.RegisterTestSolutionService;
import lombok.AllArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v3/logged/solutions/test")
@Secured("ROLE_STUDENT")
@AllArgsConstructor
public class RegisterTestSolutionResource {

    private final RegisterTestSolutionService testSolutionService;

    @PostMapping("/test")
    public SolutionResponse checkTest(@Valid @RequestBody List<CorrectAnswerCheckRequest> solutions) {
        try {
            return testSolutionService.checkAndSaveTest(solutions);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/stats")
    public SolutionStatsResponse getTestSolutionsStatsForLoggedUser() {
        return testSolutionService.getTestSolutionsStatsForLoggedStudent();
    }
}
