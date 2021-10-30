package dev.mazurkiewicz.florystyka.solution.open.web;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.solution.util.Solution;
import lombok.Value;

import javax.validation.constraints.Min;

@Value
public class CorrectAnswerCheckRequest implements Solution {

    @Min(1)
    Long questionId;
    AnswerType selectedAnswer;

}
