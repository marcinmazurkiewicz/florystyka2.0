package dev.mazurkiewicz.florystyka.solution;

import org.springframework.stereotype.Component;

@Component
public class SolutionMapper {

    public SolutionResponse mapEntityToResponse(Solution entity) {
        SolutionResponse response = new SolutionResponse();
        response.setQuestionId(entity.getId());
        response.setCorrect(entity.getCorrect());
        return response;
    }
}
