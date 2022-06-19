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
    @GeneratedValue(generator = "question_id")
    private Integer id;
    private String content;
    @Column(name = "answer_a")
    private String answerA;
    @Column(name = "answer_b")
    private String answerB;
    @Column(name = "answer_c")
    private String answerC;
    @Column(name = "answer_d")
    private String answerD;
    @Enumerated(EnumType.STRING)
    private AnswerType correct;
    private String img;
    private Integer year;
    private Integer month;
}
