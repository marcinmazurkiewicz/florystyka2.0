package dev.mazurkiewicz.quizer.solution.register.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterSingleSolutionRepository extends JpaRepository<RegisterSingleSolution, Long> {

    List<RegisterSingleSolution> findAllByStudentId(Long studentId);
}
