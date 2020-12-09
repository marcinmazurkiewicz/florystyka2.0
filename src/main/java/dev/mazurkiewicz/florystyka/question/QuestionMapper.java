package dev.mazurkiewicz.florystyka.question;

import dev.mazurkiewicz.florystyka.answer.Answer;
import dev.mazurkiewicz.florystyka.answer.AnswerType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {

    public QuestionResponse mapEntityToResponse(Question entity) {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(AnswerType.A, entity.getAnswerA()));
        answers.add(new Answer(AnswerType.B, entity.getAnswerB()));
        answers.add(new Answer(AnswerType.C, entity.getAnswerC()));
        answers.add(new Answer(AnswerType.D, entity.getAnswerD()));
        String img = null;
        if (entity.getImg() != null) {
            img = String.format("/resources/img/%s", entity.getImg());
        }
        return new QuestionResponse(entity.getId(), entity.getContent(), answers, img);
    }
}

