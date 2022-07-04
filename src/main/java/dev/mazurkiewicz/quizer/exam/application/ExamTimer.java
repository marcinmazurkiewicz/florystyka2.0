package dev.mazurkiewicz.quizer.exam.application;

import dev.mazurkiewicz.quizer.exam.domain.model.ExamDuration;

public record ExamTimer(int hours, int minutes, int seconds) {

    public static ExamTimer of(ExamDuration duration) {
        return new ExamTimer(duration.hours().value(),
                duration.minutes().value(),
                duration.seconds().value());
    }
}
