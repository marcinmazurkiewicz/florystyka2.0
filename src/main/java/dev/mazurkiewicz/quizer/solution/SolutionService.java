package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import dev.mazurkiewicz.quizer.questions.AnswerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SolutionService {

    private final SolutionRepository repository;

    public SolutionResponse checkSingleAnswer(SolutionRequest solution) {
        AnswerType selectedAnswer = AnswerType.of(solution.selectedAnswer());
        return repository
                .findById(solution.questionId())
                .map(s -> new SolutionResponse(s.getId(), s.getCorrect(), selectedAnswer))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Question with id %d doesn't exist", solution.questionId())));
    }

    public TestSolutionResponse checkTest(List<SolutionRequest> solutions) {
        Map<Integer, SolutionResponse> solutionMap = solutions
                .stream()
                .map(this::checkSingleAnswer)
                .collect(Collectors.toMap(SolutionResponse::questionId, Function.identity()));
        long points = solutionMap.values().stream().filter(s -> s.correct().equals(s.selected())).count();

        return new TestSolutionResponse(points, solutionMap.size(), solutionMap);
    }
}
