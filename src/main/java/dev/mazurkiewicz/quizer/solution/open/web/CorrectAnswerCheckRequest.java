package dev.mazurkiewicz.quizer.solution.open.web;

import dev.mazurkiewicz.quizer.answer.AnswerType;
import dev.mazurkiewicz.quizer.solution.util.Solution;
import lombok.Value;

import javax.validation.constraints.Min;

@Value
public class CorrectAnswerCheckRequest implements Solution {

    @Min(1)
    Long questionId;
    AnswerType selectedAnswer;

}
