package dev.mazurkiewicz.quizer.exam.domain.model;

public class ExamDuration {
    private final ExamDurationHour hours;
    private final ExamDurationMinute minutes;
    private final ExamDurationSecond seconds;

    private ExamDuration(ExamDurationSecond seconds) {
        int secs = seconds.value();
        int hrs = secs / 3600;
        secs -= hrs * 3600;
        int mins = secs / 60;
        secs %= 60;
        hours = new ExamDurationHour(hrs);
        minutes = new ExamDurationMinute(mins);
        this.seconds = new ExamDurationSecond(secs);
    }

    public static ExamDuration fromSeconds(int seconds) {
        return new ExamDuration(new ExamDurationSecond(seconds));
    }

    public ExamDurationHour hours() {
        return hours;
    }

    public ExamDurationMinute minutes() {
        return minutes;
    }

    public ExamDurationSecond seconds() {
        return seconds;
    }

    public Integer toSeconds() {
        return hours.value() * 3600 + minutes().value() * 60 + seconds.value();
    }

    public record ExamDurationHour(int value) {
    }

    public record ExamDurationMinute(int value) {
    }

    public record ExamDurationSecond(int value) {
    }
}
