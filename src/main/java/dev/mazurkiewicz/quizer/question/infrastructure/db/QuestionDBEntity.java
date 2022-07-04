package dev.mazurkiewicz.quizer.question.infrastructure.db;

import dev.mazurkiewicz.quizer.question.application.AnswerStatus;
import dev.mazurkiewicz.quizer.question.domain.model.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
public class QuestionDBEntity {

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

    public static QuestionDBEntity of(Question question) {

        return QuestionDBEntity.builder()
                .id(question.getId().value())
                .content(question.getContent().value())
                .answerA(ExtractionAnswerUtil.extractAnswerFromList(question.getAnswers(), AnswerType.A))
                .answerB(ExtractionAnswerUtil.extractAnswerFromList(question.getAnswers(), AnswerType.B))
                .answerC(ExtractionAnswerUtil.extractAnswerFromList(question.getAnswers(), AnswerType.C))
                .answerD(ExtractionAnswerUtil.extractAnswerFromList(question.getAnswers(), AnswerType.D))
                .correct(ExtractionAnswerUtil.extractCorrectAnswerTypeFromList(question.getAnswers()))
                .img(question.getImage().path())
                .year(question.getSourceExamDate().sourceExamYear())
                .month(question.getSourceExamDate().sourceExamMonth())
                .build();
    }

    public Question toDomain() {
        return new Question(new QuestionId(id),
                new QuestionContent(content),
                List.of(new Answer(AnswerType.A, new AnswerContent(answerA), AnswerStatus.of(correct == AnswerType.A)),
                        new Answer(AnswerType.B, new AnswerContent(answerB), AnswerStatus.of(correct == AnswerType.B)),
                        new Answer(AnswerType.C, new AnswerContent(answerC), AnswerStatus.of(correct == AnswerType.C)),
                        new Answer(AnswerType.D, new AnswerContent(answerD), AnswerStatus.of(correct == AnswerType.D))),
                new QuestionImage(img),
                new QuestionSourceExamDate(month, year));
    }
}
