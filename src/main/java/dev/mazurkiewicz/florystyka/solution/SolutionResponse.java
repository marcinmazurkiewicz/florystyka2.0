package dev.mazurkiewicz.florystyka.solution;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import lombok.Value;

@Value
public class SolutionResponse {

    Integer questionId;
    AnswerType correct;
    AnswerType selected;

}
