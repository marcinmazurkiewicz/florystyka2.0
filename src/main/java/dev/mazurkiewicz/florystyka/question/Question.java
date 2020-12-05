package dev.mazurkiewicz.florystyka.question;

import dev.mazurkiewicz.florystyka.answer.AnswerType;

import javax.persistence.*;

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
    @Enumerated(EnumType.STRING)
    private AnswerType correct;
    private String img;
    private Integer year;
    private Integer month;


    public Question() {
    }

    public Question(int id, String content, String answerA, String answerB, String answerC, String answerD, AnswerType correct) {
        this.id = id;
        this.content = content;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correct = correct;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", content=" + content + ", answers=" + answerA + ", " + answerB + ", " + answerC
                + ", " + answerD + ", correct=" + correct
                + ", img=" + img + "]";
    }


}
