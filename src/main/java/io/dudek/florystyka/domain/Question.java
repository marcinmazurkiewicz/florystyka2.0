package io.dudek.florystyka.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue
    private int id;
    private String content;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    @Transient
    private List<Answer> answers;
    @Column(name = "ok")
    @Enumerated(EnumType.STRING)
    private AnswerType correct;
    private String img;


    public Question() {}

    public Question(int id, String content, String answerA, String answerB, String answerC, String answerD, AnswerType correct) {
        this.id = id;
        this.content = content;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correct = correct;
        generateAnswers();
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

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public AnswerType getCorrect() {
        return correct;
    }

    public void setCorrect(AnswerType correct) {
        this.correct = correct;
    }

    public List<Answer> getAnswers() {
        if (answers == null) {
            generateAnswers();
        }
        return answers;
    }

    public void setAnswers(List<Answer> answer) {
       this.answers = answer;
    }

    public String getAnswerContent(int index) {
        if (answers == null) {
            generateAnswers();
        }
       return this.answers.get(index).getContent();
    }

    public AnswerType getAnswerType(int index) {
        if (answers == null) {
            generateAnswers();
        }
        return this.answers.get(index).getValue();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", content=" + content + ", answers=" + answerA + ", " + answerB + ", " + answerC
                +", " + answerD + ", correct=" + correct
                + ", img=" + img + "]";
    }

    private void generateAnswers() {
        answers = new ArrayList<>();
        answers.add(new Answer(AnswerType.A, answerA));
        answers.add(new Answer(AnswerType.B, answerB));
        answers.add(new Answer(AnswerType.C, answerC));
        answers.add(new Answer(AnswerType.D, answerD));
    }


}
