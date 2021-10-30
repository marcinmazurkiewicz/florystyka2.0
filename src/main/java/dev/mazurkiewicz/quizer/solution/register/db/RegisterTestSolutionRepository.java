package dev.mazurkiewicz.quizer.solution.register.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterTestSolutionRepository extends JpaRepository<RegisterTestSolution, Long> {

    List<RegisterTestSolution> findAllByStudentId(Long studentId);
}
