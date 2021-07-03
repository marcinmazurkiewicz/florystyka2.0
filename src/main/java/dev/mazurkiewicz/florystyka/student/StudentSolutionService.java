package dev.mazurkiewicz.florystyka.student;

import dev.mazurkiewicz.florystyka.auth.UserAuthHelper;
import dev.mazurkiewicz.florystyka.solution.SolutionRequest;
import dev.mazurkiewicz.florystyka.solution.SolutionResponse;
import dev.mazurkiewicz.florystyka.solution.SolutionService;
import dev.mazurkiewicz.florystyka.user.LoggedUserDetails;
import org.springframework.stereotype.Service;

@Service
public class StudentSolutionService {

    private final SolutionService solutionService;
    private final StudentSolutionRepository repository;
    private final UserAuthHelper userAuthHelper;

    public StudentSolutionService(SolutionService solutionService, StudentSolutionRepository repository, UserAuthHelper userAuthHelper) {
        this.solutionService = solutionService;
        this.repository = repository;
        this.userAuthHelper = userAuthHelper;
    }

    public SolutionResponse checkAndSaveSingle(SolutionRequest request) {
        LoggedUserDetails loggedUser = userAuthHelper.getLoggedUser();
        StudentSolution studentSolution = new StudentSolution(loggedUser.getId(), request.getQuestionId(), request.getSelectedAnswer());
        repository.save(studentSolution);
        return solutionService.checkSingleAnswer(request);
    }
}