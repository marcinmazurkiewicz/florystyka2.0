package dev.mazurkiewicz.florystyka.student;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "students_solutions")
@SequenceGenerator(
        name = "SolutionGenerator",
        sequenceName = "solution_seq",
        allocationSize = 1
)
public class StudentSolution {

    @Id
    @GeneratedValue(generator = "SolutionGenerator")
    private Long id;
    private Long userId;
    private Long questionId;
    @Enumerated(EnumType.STRING)
    private AnswerType selected;

    public StudentSolution(Long userId, Long questionId, AnswerType selected) {
        this.userId = userId;
        this.selected = selected;
        this.questionId = questionId;
    }
}
