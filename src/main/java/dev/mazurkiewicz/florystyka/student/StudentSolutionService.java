package dev.mazurkiewicz.florystyka.student;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.auth.UserAuthHelper;
import dev.mazurkiewicz.florystyka.solution.SolutionRequest;
import dev.mazurkiewicz.florystyka.solution.SolutionResponse;
import dev.mazurkiewicz.florystyka.solution.SolutionService;
import dev.mazurkiewicz.florystyka.solution.SolutionStatsResponse;
import dev.mazurkiewicz.florystyka.user.LoggedUserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public SolutionStatsResponse getSingleSolutionsStatsForLoggedStudent() {
        LoggedUserDetails loggedUser = userAuthHelper.getLoggedUser();
        List<StudentSolution> solutions = repository.findAllByStudentId(loggedUser.getId());
        Set<Long> questionIds = solutions.stream().map(StudentSolution::getQuestionId).collect(Collectors.toSet());
        Map<Long, AnswerType> solutionsMap = solutionService.getSolutionsMap(questionIds);
        long correctAnswers = solutions.stream().filter(s -> solutionsMap.get(s.getQuestionId()) == s.getSelected()).count();
        return new SolutionStatsResponse(Math.toIntExact(correctAnswers), solutions.size());
    }

    public SolutionResponse checkAndSaveSingle(SolutionRequest request) {
        LoggedUserDetails loggedUser = userAuthHelper.getLoggedUser();
        StudentSolution studentSolution = new StudentSolution(loggedUser.getId(), request.getQuestionId(), request.getSelectedAnswer());
        repository.save(studentSolution);
        return solutionService.checkSingleAnswer(request);
    }
}