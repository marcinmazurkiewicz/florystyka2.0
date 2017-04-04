package io.dudek.florystyka.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int id;
    private String content;
    private List<Answer> answers;
    private AnswerType correct;
    private String img;


    public Question() {
        answers = new ArrayList<>();
    }

    public Question(int id, String content, List<Answer> answers, AnswerType correct) {
        this.id = id;
        this.content = content;
        this.answers = answers;
        this.correct = correct;
    }

    public Question(int id, String content, List<Answer> answers, AnswerType correct, String imgPath) {
        this(id, content, answers, correct);
        this.img = imgPath;
    }
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
        return this.answers;
    }

    public void setAnswers(List<Answer> answer) {
        this.answers = answer;
    }

    public String getAnswerContent(int index) {
        return this.answers.get(index).getContent();
    }

    public AnswerType getAnswerType(int index) {
        return this.answers.get(index).getValue();
    }

    public void setAnswer(int index, AnswerType type, String answer) {
        this.answers.get(index).setContent(answer);
        this.answers.get(index).setValue(type);
    }

    public void addAnswer(AnswerType type, String answer) {
        this.answers.add(new Answer(type, answer));
    }

    public AnswerType getCorrect() {
        return correct;
    }

    public void setCorrect(AnswerType correct) {
        this.correct = correct;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", content=" + content + ", answers=" + answers + ", correct=" + correct
                + ", img=" + img + "]";
    }


}
