package dev.mazurkiewicz.florystyka.solution;

import lombok.Value;

import javax.validation.constraints.Min;

@Value
public class SolutionRequest {

    @Min(1)
    int questionId;
    String selectedAnswer;

}
