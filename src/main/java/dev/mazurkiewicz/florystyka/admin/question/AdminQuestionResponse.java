package dev.mazurkiewicz.florystyka.admin.question;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.question.Question;
import lombok.Value;

@Value
public class AdminQuestionResponse {

    Integer id;
    String content;
    String answerA;
    String answerB;
    String answerC;
    String answerD;
    AnswerType correct;
    String image;
    Integer month;
    Integer year;

    public AdminQuestionResponse(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
        this.answerA = question.getAnswerA();
        this.answerB = question.getAnswerB();
        this.answerC = question.getAnswerC();
        this.answerD = question.getAnswerD();
        this.correct = question.getCorrect();
        if (question.getImg() != null && !question.getImg().isEmpty()) {
            this.image = String.format("/resources/img/%s", question.getImg());
        } else {
            this.image = null;
        }
        this.month = question.getMonth();
        this.year = question.getYear();
    }

}
