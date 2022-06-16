package dev.mazurkiewicz.quizer.questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

    /*
     * Getting randoms questions by ORDER BY RAND() query is more efficient than service-side methods
     * to get random results because database is small and and there is little chance of its grow
     * that much to changing the performance of this solution
     * */
    @Query(value = "SELECT * FROM questions ORDER BY RANDOM() LIMIT :howMany", nativeQuery = true)
    Set<QuestionEntity> getRandomQuestions(int howMany);

    @Query(value = "SELECT MIN(q.year) FROM QuestionEntity AS q")
    Integer getEarliestYear();

    @Query(value = "SELECT MAX(q.year) FROM QuestionEntity AS q")
    Integer getLatestYear();
}
