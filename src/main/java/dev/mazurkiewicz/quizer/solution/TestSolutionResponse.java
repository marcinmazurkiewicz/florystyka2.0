package dev.mazurkiewicz.quizer.solution;

import java.util.Map;

public record TestSolutionResponse(long points, int total, Map<Integer, SolutionResponse> solutions) {

}
