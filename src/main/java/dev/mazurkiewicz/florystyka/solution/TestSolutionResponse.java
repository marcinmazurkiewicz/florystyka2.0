package dev.mazurkiewicz.florystyka.solution;

import lombok.Value;

import java.util.Map;

@Value
public class TestSolutionResponse {

    long points;
    int total;
    Map<Integer, SolutionResponse> solutions;
}
