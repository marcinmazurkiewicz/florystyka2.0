package dev.mazurkiewicz.florystyka.solution;

import dev.mazurkiewicz.florystyka.answer.AnswerType;

public class SolutionResponse {

    private Integer questionId;
    private AnswerType correct;
    private AnswerType selected;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public AnswerType getCorrect() {
        return correct;
    }

    public void setCorrect(AnswerType correct) {
        this.correct = correct;
    }

    public AnswerType getSelected() {
        return selected;
    }

    public void setSelected(AnswerType selected) {
        this.selected = selected;
    }
}
