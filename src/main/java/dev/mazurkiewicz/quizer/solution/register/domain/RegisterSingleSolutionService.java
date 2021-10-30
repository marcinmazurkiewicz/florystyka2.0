package dev.mazurkiewicz.quizer.solution.register.domain;

import dev.mazurkiewicz.quizer.answer.AnswerType;
import dev.mazurkiewicz.quizer.auth.UserAuthHelper;
import dev.mazurkiewicz.quizer.solution.open.web.CorrectAnswerCheckRequest;
import dev.mazurkiewicz.quizer.solution.open.web.SolutionResponse;
import dev.mazurkiewicz.quizer.solution.open.domain.AnswerCheckingService;
import dev.mazurkiewicz.quizer.solution.open.web.SolutionStatsResponse;
import dev.mazurkiewicz.quizer.solution.register.db.RegisterSingleSolution;
import dev.mazurkiewicz.quizer.solution.register.db.RegisterSingleSolutionRepository;
import dev.mazurkiewicz.quizer.solution.util.SolutionUtil;
import dev.mazurkiewicz.quizer.user.LoggedUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegisterSingleSolutionService {

    private final AnswerCheckingService answerCheckingService;
    private final RegisterSingleSolutionRepository registerSingleSolutionRepository;
    private final UserAuthHelper userAuthHelper;
    private final SolutionUtil solutionUtil;

    public SolutionStatsResponse getSingleSolutionsStatsForLoggedStudent() {
        LoggedUserDetails loggedUser = userAuthHelper.getLoggedUser();
        List<RegisterSingleSolution> solutions = registerSingleSolutionRepository.findAllByStudentId(loggedUser.getId());
        Set<Long> questionIds = solutionUtil.getQuestionsId(solutions);
        Map<Long, AnswerType> solutionsMap = answerCheckingService.getSolutionsMap(questionIds);
        long correctAnswers = solutions.stream().filter(s -> solutionsMap.get(s.getQuestionId()) == s.getSelected()).count();
        return new SolutionStatsResponse(Math.toIntExact(correctAnswers), solutions.size());
    }

    public SolutionResponse checkAndSaveSingle(CorrectAnswerCheckRequest request) {
        LoggedUserDetails loggedUser = userAuthHelper.getLoggedUser();
        RegisterSingleSolution registerSingleSolution = new RegisterSingleSolution(loggedUser.getId(), request.getQuestionId(), request.getSelectedAnswer());
        registerSingleSolutionRepository.save(registerSingleSolution);
        return answerCheckingService.checkSingleAnswer(request);
    }
}