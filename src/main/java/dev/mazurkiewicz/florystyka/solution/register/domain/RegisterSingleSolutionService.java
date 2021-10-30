package dev.mazurkiewicz.florystyka.solution.register.domain;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.auth.UserAuthHelper;
import dev.mazurkiewicz.florystyka.solution.open.web.CorrectAnswerCheckRequest;
import dev.mazurkiewicz.florystyka.solution.open.web.SolutionResponse;
import dev.mazurkiewicz.florystyka.solution.open.domain.AnswerCheckingService;
import dev.mazurkiewicz.florystyka.solution.open.web.SolutionStatsResponse;
import dev.mazurkiewicz.florystyka.solution.register.db.RegisterSingleSolution;
import dev.mazurkiewicz.florystyka.solution.register.db.RegisterSingleSolutionRepository;
import dev.mazurkiewicz.florystyka.solution.util.SolutionUtil;
import dev.mazurkiewicz.florystyka.user.LoggedUserDetails;
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