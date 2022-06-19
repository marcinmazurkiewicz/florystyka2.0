package dev.mazurkiewicz.quizer.questions;

public record ExamTimer(int minutes, int seconds) {

    public static ExamTimer fromSeconds(int secs) {
        int minutes = secs / 60;
        int seconds = secs % 60;
        return new ExamTimer(minutes, seconds);
    }
}
