package dev.mazurkiewicz.florystyka.solution;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mazurkiewicz.florystyka.answer.AnswerType;
import lombok.Value;

import java.util.Map;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolutionResponse {

    Integer points;
    Integer total;
    Map<Integer, AnswerType> solutions;

    public SolutionResponse(Integer points, Integer total, Map<Integer, AnswerType> solutions) {
        this.points = points;
        this.total = total;
        this.solutions = solutions;
    }

    public SolutionResponse(Map<Integer, AnswerType> solutions) {
        this(null, null, solutions);
    }

}
