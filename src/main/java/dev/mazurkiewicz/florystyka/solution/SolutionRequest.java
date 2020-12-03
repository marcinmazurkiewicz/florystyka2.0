package dev.mazurkiewicz.florystyka.solution;

import dev.mazurkiewicz.florystyka.answer.AnswerType;

public class SolutionRequest {

    private int questionId;
    private AnswerType selectedAnswer;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public AnswerType getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(AnswerType selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
