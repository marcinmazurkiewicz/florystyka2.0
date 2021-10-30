package dev.mazurkiewicz.florystyka.question.admin.question;

import dev.mazurkiewicz.florystyka.question.open.db.Question;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminQuestionRepository extends PagingAndSortingRepository<Question, Integer> {
}
