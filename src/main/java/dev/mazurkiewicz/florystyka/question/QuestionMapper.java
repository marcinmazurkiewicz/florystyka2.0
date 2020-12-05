package dev.mazurkiewicz.florystyka.question;

import dev.mazurkiewicz.florystyka.answer.Answer;
import dev.mazurkiewicz.florystyka.answer.AnswerType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {

    public QuestionResponse mapEntityToResponse(Question entity) {
        QuestionResponse response = new QuestionResponse();
        response.setId(entity.getId());
        response.setContent(entity.getContent());
        response.setImg(entity.getImg());
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(AnswerType.A, entity.getAnswerA()));
        answers.add(new Answer(AnswerType.B, entity.getAnswerB()));
        answers.add(new Answer(AnswerType.C, entity.getAnswerC()));
        answers.add(new Answer(AnswerType.D, entity.getAnswerD()));
        response.setAnswers(answers);
        return response;
    }
}

