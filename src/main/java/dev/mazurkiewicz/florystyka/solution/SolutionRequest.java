package dev.mazurkiewicz.florystyka.solution;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import lombok.Value;

import javax.validation.constraints.Min;

@Value
public class SolutionRequest {

    @Min(1)
    int questionId;
    AnswerType selectedAnswer;

}
