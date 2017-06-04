package io.dudek.florystyka.db;

import io.dudek.florystyka.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>, TestQuestion {

    Question findQuestionById(Integer id);
    Integer countQuestionByIdIsGreaterThanEqual(Integer id);
    List<Question> findAllById(Iterable<Integer> ids);


}