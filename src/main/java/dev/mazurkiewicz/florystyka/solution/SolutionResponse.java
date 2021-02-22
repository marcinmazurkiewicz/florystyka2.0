package dev.mazurkiewicz.florystyka.solution;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import lombok.Value;

import java.util.Map;

@Value
public class SolutionResponse {

    long points;
    int total;
    Map<Integer, AnswerType> solutions;

}
