package dev.mazurkiewicz.florystyka.question;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "questions")
@SequenceGenerator(
        name = "question_id",
        sequenceName = "question_seq",
        allocationSize = 1
)
public class Question {

    @Id
    @GeneratedValue(generator = "question_id")
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
