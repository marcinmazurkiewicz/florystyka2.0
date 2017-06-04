package io.dudek.florystyka.db;

import io.dudek.florystyka.domain.Question;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuestionRepositoryImpl implements TestQuestion {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Question> findTestQuestions(Set<Integer> ids) {
        List<Question> result = new ArrayList<>();
        for(Integer id : ids) {
            result.add(em.find(Question.class, id));
        }
        return result;
    }
}
