package dev.mazurkiewicz.florystyka.student;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

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
    private Long studentId;
    private Long questionId;
    @Enumerated(EnumType.STRING)
    private AnswerType selected;
    private Instant timestamp;

    public StudentSolution(Long studentId, Long questionId, AnswerType selected) {
        this.studentId = studentId;
        this.selected = selected;
        this.questionId = questionId;
        this.timestamp = Instant.now();
    }
}
