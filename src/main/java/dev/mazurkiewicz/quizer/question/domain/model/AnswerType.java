package dev.mazurkiewicz.quizer.question.domain.model;

import java.util.Arrays;

public enum AnswerType {
    A, B, C, D, EMPTY;

    public static AnswerType of(String answer) {
        if (answer == null || answer.isBlank()) return EMPTY;

        return Arrays.stream(values())
                .filter(x -> answer.equalsIgnoreCase(x.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Answer outside the cafeteria!"));
    }
}
