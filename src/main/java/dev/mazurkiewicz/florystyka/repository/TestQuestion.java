package dev.mazurkiewicz.florystyka.repository;

import dev.mazurkiewicz.florystyka.model.Question;

import java.util.List;
import java.util.Set;

public interface TestQuestion {
    List<Question> findTestQuestions(Set<Integer> ids);
}
