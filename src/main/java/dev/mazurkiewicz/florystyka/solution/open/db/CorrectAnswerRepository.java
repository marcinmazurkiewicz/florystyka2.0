package dev.mazurkiewicz.florystyka.solution.open.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer, Long> {
}
