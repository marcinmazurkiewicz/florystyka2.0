package dev.mazurkiewicz.quizer.question.open.domain;

import dev.mazurkiewicz.quizer.question.admin.question.NewQuestionRequest;
import dev.mazurkiewicz.quizer.answer.Answer;
import dev.mazurkiewicz.quizer.answer.AnswerType;
import dev.mazurkiewicz.quizer.question.open.db.Question;
import dev.mazurkiewicz.quizer.question.open.web.QuestionResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {

    public QuestionResponse mapEntityToResponse(Question entity) {
        if (entity == null)
            throw new IllegalStateException("Question cannot be empty");

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(AnswerType.A, entity.getAnswerA()));
        answers.add(new Answer(AnswerType.B, entity.getAnswerB()));
        answers.add(new Answer(AnswerType.C, entity.getAnswerC()));
        answers.add(new Answer(AnswerType.D, entity.getAnswerD()));
        String img = null;
        if (entity.getImg() != null && !entity.getImg().isEmpty()) {
            img = String.format("/resources/img/%s", entity.getImg());
        }
        return new QuestionResponse(entity.getId(), entity.getContent(), answers, img);
    }

    public Question mapRequestToEntity(NewQuestionRequest questionRequest) {
        Question result = new Question();
        result.setContent(questionRequest.getContent());
        result.setAnswerA(questionRequest.getAnswerA());
        result.setAnswerB(questionRequest.getAnswerB());
        result.setAnswerC(questionRequest.getAnswerC());
        result.setAnswerD(questionRequest.getAnswerD());
        result.setCorrect(questionRequest.getCorrect());
        result.setMonth(questionRequest.getMonth());
        result.setYear(questionRequest.getYear());
        return result;
    }
}

