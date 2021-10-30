package dev.mazurkiewicz.quizer.solution.register.db;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "students_test_solutions")
public class RegisterTestSolution {

    @Id
    @GeneratedValue(generator = "SolutionGenerator")
    private Long id;
    private Long studentId;
    private Instant timestamp;

    @OneToMany
    @JoinColumn(name = "test_id")
    private Set<RegisterSingleSolution> solutions;

    public RegisterTestSolution(Long studentId) {
        this.studentId = studentId;
        this.timestamp = Instant.now();
    }
}
