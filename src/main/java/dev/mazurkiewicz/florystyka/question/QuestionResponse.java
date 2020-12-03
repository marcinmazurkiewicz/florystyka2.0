package dev.mazurkiewicz.florystyka.question;

import dev.mazurkiewicz.florystyka.answer.Answer;

import java.util.List;

public class QuestionResponse {

    private int id;
    private String content;
    private List<Answer> answers;
    private String img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
