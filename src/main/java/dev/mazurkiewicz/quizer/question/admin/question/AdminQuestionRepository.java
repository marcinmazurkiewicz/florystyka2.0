package dev.mazurkiewicz.quizer.question.admin.question;

import dev.mazurkiewicz.quizer.question.open.db.Question;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminQuestionRepository extends PagingAndSortingRepository<Question, Integer> {
}
