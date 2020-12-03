package dev.mazurkiewicz.florystyka.repository;

import dev.mazurkiewicz.florystyka.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    /*
     * Getting randoms questions by ORDER BY RAND() query is more efficient than service-side methods
     * to get random results because database is small and and there is little chance of its grow
     * that much to changing the performance of this solution
     * */
    @Query(value = "SELECT * FROM questions ORDER BY RAND() LIMIT :howMany", nativeQuery = true)
    List<Question> getRandomQuestions(int howMany);

}