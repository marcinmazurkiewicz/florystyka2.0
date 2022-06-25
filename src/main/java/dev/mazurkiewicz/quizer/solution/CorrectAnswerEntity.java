package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.questions.AnswerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "correct_answers")
public class CorrectAnswerEntity {

    @Id
    Integer questionId;
    @Enumerated(EnumType.STRING)
    private AnswerType correct;
}
