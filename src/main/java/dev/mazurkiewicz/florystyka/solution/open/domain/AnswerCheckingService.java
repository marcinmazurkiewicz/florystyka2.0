package dev.mazurkiewicz.florystyka.solution.open.domain;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import dev.mazurkiewicz.florystyka.solution.open.db.CorrectAnswer;
import dev.mazurkiewicz.florystyka.solution.open.db.CorrectAnswerRepository;
import dev.mazurkiewicz.florystyka.solution.open.web.CorrectAnswerCheckRequest;
import dev.mazurkiewicz.florystyka.solution.open.web.SolutionResponse;
import dev.mazurkiewicz.florystyka.solution.util.SolutionUtil;
import lombok.AllArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnswerCheckingService {

    private final CorrectAnswerRepository repository;
    private final SolutionUtil solutionUtil;

    public SolutionResponse checkSingleAnswer(CorrectAnswerCheckRequest solution) {
        return repository
                .findById(solution.getQuestionId())
                .map(s -> new SolutionResponse(Map.of(s.getQuestionId(), s.getCorrect())))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Question with id %d doesn't exist", solution.getQuestionId())));

    }

    public SolutionResponse checkTest(List<CorrectAnswerCheckRequest> solutions) {
        Set<Long> questionIds = solutionUtil.getQuestionsId(solutions);
        Map<Long, AnswerType> solutionMap = getSolutionsMap(questionIds);

        if (!solutionMap.keySet().containsAll(questionIds)) {
            throw new IncorrectResultSizeDataAccessException("Some answers are missing", questionIds.size(), solutionMap.size());
        }

        long points = solutions
                .stream()
                .filter(s -> s.getSelectedAnswer().equals(solutionMap.get(s.getQuestionId())))
                .count();

        return new SolutionResponse(Long.valueOf(points).intValue(), solutionMap.size(), solutionMap);
    }

    public Map<Long, AnswerType> getSolutionsMap(Set<Long> questionIds) {
        return repository.findAllById(questionIds).stream()
                .collect(Collectors.toMap(CorrectAnswer::getQuestionId, CorrectAnswer::getCorrect));
    }
}
