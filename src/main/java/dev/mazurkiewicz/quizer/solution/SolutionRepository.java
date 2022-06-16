package dev.mazurkiewicz.quizer.solution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepository extends JpaRepository<CorrectAnswerEntity, Integer> {
}
