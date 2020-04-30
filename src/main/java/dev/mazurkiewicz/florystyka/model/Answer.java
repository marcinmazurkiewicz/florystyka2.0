package dev.mazurkiewicz.florystyka.model;

import javax.validation.constraints.NotEmpty;

public class Answer {

    @NotEmpty
    private AnswerType value;
    private String content;

    public Answer(){}

    public Answer(AnswerType value, String content) {
        this.value = value;
        this.content = content;
    }
    public AnswerType getValue() {
        return value;
    }
    public void setValue(AnswerType value) {
        this.value = value;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}