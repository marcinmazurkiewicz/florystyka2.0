package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.questions.AnswerType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "correct_answers")
public class CorrectAnswerEntity {

    @Id
    Integer id;
    @Enumerated(EnumType.STRING)
    private AnswerType correct;
}
