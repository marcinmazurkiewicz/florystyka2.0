package dev.mazurkiewicz.florystyka.solution;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SolutionService {

    private final SolutionRepository repository;
    private final SolutionMapper mapper;

    public SolutionService(SolutionRepository repository, SolutionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SolutionResponse checkSingleAnswer(SolutionRequest solution) {
        SolutionResponse response = repository
                .findById(solution.getQuestionId())
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Question with id %d doesn't exist", solution.getQuestionId())));
        AnswerType selectedAnswer;
        try {
            selectedAnswer = AnswerType.valueOf(solution.getSelectedAnswer().toUpperCase());
        } catch (IllegalArgumentException e) {
            selectedAnswer = AnswerType.EMPTY;
        }
        response.setSelected(selectedAnswer);
        return response;
    }

    public TestSolutionResponse checkTest(List<SolutionRequest> solutions) {
        Map<Integer, SolutionResponse> solutionMap = solutions
                .stream()
                .map(this::checkSingleAnswer)
                .collect(Collectors.toMap(SolutionResponse::getQuestionId, Function.identity()));
        long points = solutionMap.values().stream().filter(s -> s.getCorrect().equals(s.getSelected())).count();

        return new TestSolutionResponse(points, solutionMap.size(), solutionMap);
    }
}
