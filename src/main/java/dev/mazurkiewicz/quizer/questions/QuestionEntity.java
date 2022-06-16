package dev.mazurkiewicz.quizer.questions;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue
    private Integer id;
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
}
