package dev.mazurkiewicz.florystyka.solution;

import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

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
        response.setSelected(solution.getSelectedAnswer());
        return response;
    }
}
