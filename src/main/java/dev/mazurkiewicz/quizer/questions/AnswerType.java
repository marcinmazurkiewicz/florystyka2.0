package dev.mazurkiewicz.quizer.questions;

import java.util.Arrays;

public enum AnswerType {
    A, B, C, D, EMPTY;

    public static AnswerType of(String answer) {
        return Arrays.stream(values())
                .filter(x -> answer.equalsIgnoreCase(x.name()))
                .findAny()
                .orElse(EMPTY);
    }
}
