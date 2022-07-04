package dev.mazurkiewicz.quizer.exam.domain.model;

public record AvailablePoints(int value) {
    public static AvailablePoints of(int size) {
        return new AvailablePoints(size);
    }
}
