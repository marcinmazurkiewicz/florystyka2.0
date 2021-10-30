package dev.mazurkiewicz.florystyka.solution.register.domain;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.auth.UserAuthHelper;
import dev.mazurkiewicz.florystyka.solution.open.domain.AnswerCheckingService;
import dev.mazurkiewicz.florystyka.solution.open.web.CorrectAnswerCheckRequest;
import dev.mazurkiewicz.florystyka.solution.open.web.SolutionResponse;
import dev.mazurkiewicz.florystyka.solution.open.web.SolutionStatsResponse;
import dev.mazurkiewicz.florystyka.solution.register.db.RegisterSingleSolution;
import dev.mazurkiewicz.florystyka.solution.register.db.RegisterSingleSolutionRepository;
import dev.mazurkiewicz.florystyka.solution.register.db.RegisterTestSolution;
import dev.mazurkiewicz.florystyka.solution.register.db.RegisterTestSolutionRepository;
import dev.mazurkiewicz.florystyka.solution.util.SolutionUtil;
import dev.mazurkiewicz.florystyka.user.LoggedUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegisterTestSolutionService {

    private final AnswerCheckingService answerCheckingService;
    private final RegisterSingleSolutionRepository registerSingleSolutionRepository;
    private final RegisterTestSolutionRepository registerTestSolutionRepository;
    private final UserAuthHelper userAuthHelper;
    private final SolutionUtil solutionUtil;

    public SolutionResponse checkAndSaveTest(List<CorrectAnswerCheckRequest> requestSolutions) {
        LoggedUserDetails loggedUser = userAuthHelper.getLoggedUser();
        SolutionResponse correctAnswers = answerCheckingService.checkTest(requestSolutions);
        RegisterTestSolution registerTestSolution = new RegisterTestSolution(loggedUser.getId());
        registerTestSolutionRepository.save(registerTestSolution);
        Set<RegisterSingleSolution> solutions = requestSolutions
                .stream()
                .map(solution -> new RegisterSingleSolution(loggedUser.getId(), solution.getQuestionId(), solution.getSelectedAnswer()))
                .collect(Collectors.toSet());
        registerSingleSolutionRepository.saveAll(solutions);
        registerTestSolution.setSolutions(solutions);
        registerTestSolutionRepository.save(registerTestSolution);
        return correctAnswers;
    }
    public SolutionStatsResponse getTestSolutionsStatsForLoggedStudent() {
        LoggedUserDetails loggedUser = userAuthHelper.getLoggedUser();
        List<RegisterTestSolution> tests = registerTestSolutionRepository.findAllByStudentId(loggedUser.getId());
        long passedTests = tests.stream().map(this::isTestPassed)
                .filter(x -> x)
                .count();
        return new SolutionStatsResponse(Math.toIntExact(passedTests), tests.size());
    }

    private boolean isTestPassed(RegisterTestSolution test) {
        Set<RegisterSingleSolution> solutions = test.getSolutions();
        Set<Long> questionIds = solutionUtil.getQuestionsId(solutions);
        Map<Long, AnswerType> solutionsMap = answerCheckingService.getSolutionsMap(questionIds);
        long correctAnswers = solutions.stream().filter(s -> solutionsMap.get(s.getQuestionId()) == s.getSelected()).count();
        return ((correctAnswers/solutions.size())*100) >= 70;
    }
}
