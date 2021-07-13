package dev.mazurkiewicz.florystyka.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSolutionRepository extends JpaRepository<StudentSolution, Long> {

    List<StudentSolution> findAllByStudentId(Long studentId);
}
