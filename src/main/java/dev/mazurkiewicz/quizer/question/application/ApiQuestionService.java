package dev.mazurkiewicz.quizer.question.application;

import dev.mazurkiewicz.quizer.question.domain.model.*;
import dev.mazurkiewicz.quizer.question.domain.port.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ApiQuestionService {

    private final QuestionService service;

    QuestionResponse getRandomQuestion() {
        final Question randomQuestion = service.getRandomQuestion();
        return QuestionResponse.of(randomQuestion);
    }

    QuestionResponse getQuestionById(Integer id) {
        QuestionId questionId = new QuestionId(id);
        Question question = service.getQuestion(questionId);
        return QuestionResponse.of(question);
    }

    public QuestionInfoResponse getQuestionsInfo() {
        QuestionInfo questionInfo = service.getQuestionInfo();
        return QuestionInfoResponse.of(questionInfo);
    }

    public AnswerStatusResponse checkAnswer(SelectedAnswerRequest selectedAnswerRequest) {
        QuestionId questionId = QuestionId.of(selectedAnswerRequest.questionId());
        SelectedAnswer selectedAnswer = SelectedAnswer.of(selectedAnswerRequest.selectedAnswer());
        AnswerResult answerResult = service.checkAnswer(questionId, selectedAnswer);
        return new AnswerStatusResponse(questionId.value(), answerResult.status(), answerResult.correctAnswer());
    }
}
