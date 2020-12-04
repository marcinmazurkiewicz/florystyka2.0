package dev.mazurkiewicz.florystyka.solution;

import java.util.Map;

public class TestSolutionResponse {

    private final long points;
    private final int total;
    private final Map<Integer, SolutionResponse> solutions;

    public TestSolutionResponse(long points, int total, Map<Integer, SolutionResponse> solutions) {
        this.points = points;
        this.total = total;
        this.solutions = solutions;
    }

    public long getPoints() {
        return points;
    }

    public Map<Integer, SolutionResponse> getSolutions() {
        return solutions;
    }

    public int getTotal() {
        return total;
    }
}
