package dev.mazurkiewicz.quizer.exam.domain.model;

public record AchievedPoints(int value) {
    public static AchievedPoints of(int achievedPoints) {
        return new AchievedPoints(achievedPoints);
    }
}
