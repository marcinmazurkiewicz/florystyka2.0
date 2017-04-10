package io.dudek.florystyka.model;

import java.util.List;

/**
 * Created by md on 4/10/17.
 */
public class ApiTestDTO {

    private List<Question> questions;

    public ApiTestDTO() {}

    public ApiTestDTO(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
