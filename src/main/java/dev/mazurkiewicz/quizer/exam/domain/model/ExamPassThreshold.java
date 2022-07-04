package dev.mazurkiewicz.quizer.exam.domain.model;

public record ExamPassThreshold(Integer neededPoints) {
    public static ExamPassThreshold of(Integer examPassThreshold) {
        return new ExamPassThreshold(examPassThreshold);
    }
}
