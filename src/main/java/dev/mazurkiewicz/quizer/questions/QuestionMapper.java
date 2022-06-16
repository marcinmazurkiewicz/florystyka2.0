package dev.mazurkiewicz.quizer.questions;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {

    private final static String RESOURCE_SERVER_URL = "/resources/img";

    public QuestionResponse mapEntityToResponse(QuestionEntity entity) {
        if (entity == null)
            throw new IllegalStateException("Question cannot be empty");

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(AnswerType.A, entity.getAnswerA()));
        answers.add(new Answer(AnswerType.B, entity.getAnswerB()));
        answers.add(new Answer(AnswerType.C, entity.getAnswerC()));
        answers.add(new Answer(AnswerType.D, entity.getAnswerD()));
        String img = null;
        if (entity.getImg() != null && !entity.getImg().isEmpty()) {
            img = String.format("%s/%s", RESOURCE_SERVER_URL, entity.getImg());
        }
        return new QuestionResponse(entity.getId(), entity.getContent(), answers, img);
    }
}
