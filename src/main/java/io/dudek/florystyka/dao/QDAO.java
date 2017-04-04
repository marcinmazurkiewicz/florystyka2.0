package io.dudek.florystyka.dao;

import io.dudek.florystyka.model.Question;

import java.util.List;

public interface QDAO {


    public List<Question> findAllQuestion();
    public Question findQuestionById(int id);
    public int getMaxId();
}
