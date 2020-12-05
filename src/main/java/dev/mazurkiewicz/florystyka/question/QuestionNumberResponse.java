package dev.mazurkiewicz.florystyka.question;

public class QuestionNumberResponse {

    private final long questionNumber;
    private final int earliestQuestionYear;
    private final int latestQuestionYear;


    public QuestionNumberResponse(long questionNumber, int earliestQuestionYear, int latestQuestionYear) {
        this.questionNumber = questionNumber;
        this.earliestQuestionYear = earliestQuestionYear;
        this.latestQuestionYear = latestQuestionYear;
    }

    public long getQuestionNumber() {
        return questionNumber;
    }

    public int getEarliestQuestionYear() {
        return earliestQuestionYear;
    }

    public int getLatestQuestionYear() {
        return latestQuestionYear;
    }
}
