package io.mazurkiewicz.quizer.question.infrastructure.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JpaQuestionRepository : JpaRepository<QuestionDbEntity, Int> {

    /*
        Getting randoms questions by ORDER BY RAND() query is more efficient than service-side methods
        to get random results because database is small and there is little chance of its grow
        that much to changing the performance of this solution
    */
    @Query(value = "SELECT * FROM questions ORDER BY RANDOM() LIMIT :howMany", nativeQuery = true)
    fun findRandomQuestions(howMany: Int): Set<QuestionDbEntity>

    fun findByQuestionPublicId(questionPublicId: UUID): QuestionDbEntity?
    fun findAllByQuestionPublicIdIsIn(questionPublicIds: Collection<UUID>): List<QuestionDbEntity>

}