package dev.mazurkiewicz.florystyka.solution.open.db;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "correct_answers")
public class CorrectAnswer {

    @Id
    Long questionId;
    @Enumerated(EnumType.STRING)
    private AnswerType correct;
}
