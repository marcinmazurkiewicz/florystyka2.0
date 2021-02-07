package dev.mazurkiewicz.florystyka.admin.question;

import dev.mazurkiewicz.florystyka.question.Question;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminQuestionRepository extends PagingAndSortingRepository<Question, Integer> {
}
