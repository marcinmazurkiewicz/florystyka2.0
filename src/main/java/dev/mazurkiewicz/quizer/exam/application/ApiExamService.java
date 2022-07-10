package dev.mazurkiewicz.quizer.exam.application;

import dev.mazurkiewicz.quizer.config.QuizerConfiguration;
import dev.mazurkiewicz.quizer.exam.domain.model.*;
import dev.mazurkiewicz.quizer.exam.domain.port.ExamService;
import dev.mazurkiewicz.quizer.exam.domain.port.QuestionNumber;
import dev.mazurkiewicz.quizer.question.application.QuestionResponse;
import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;
import dev.mazurkiewicz.quizer.question.domain.model.Question;
import dev.mazurkiewicz.quizer.question.domain.model.QuestionId;
import dev.mazurkiewicz.quizer.question.domain.port.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class ApiExamService {

    private final ExamService examService;
    private final QuestionService questionService;
    private final QuizerConfiguration configuration;


    ExamResponse getExam() {
        Exam exam = examService.generateExam(QuestionNumber.of(configuration.examQuestionsNumber()),
                ExamDuration.fromSeconds(configuration.examTimeInSeconds()),
                ExamPassThreshold.of(configuration.examPassThreshold()));
        List<QuestionResponse> questions = exam.questions()
                .stream()
                .map(QuestionResponse::of)
                .toList();
        return new ExamResponse(exam.examId().value(),
                ExamTimer.of(exam.duration()),
                questions);
    }

    public ExamAnswerResponse checkExam(ExamSolutionRequest examSolutionRequest) {
        Exam exam = recreateExam(examSolutionRequest);
        ExamSolution examSolution = prepareExamSolution(examSolutionRequest.selectedAnswers());
        ExamResult examResult = exam.checkExamSolution(examSolution);
        return ExamAnswerResponse.of(examResult, exam.getCorrectAnswers());
    }

    private Exam recreateExam(ExamSolutionRequest examSolutionRequest) {
        return examService.recreateExam(ExamId.of(examSolutionRequest.examId()));
    }

    private ExamSolution prepareExamSolution(Map<Integer, AnswerType> selectedAnswers) {
        Map<Question, AnswerType> examSelectedAnswerMap = selectedAnswers.entrySet()
                .stream()
                .map(entry -> {
                    Question question = questionService.getQuestion(QuestionId.of(entry.getKey()));
                    return new AbstractMap.SimpleEntry<>(question, entry.getValue());
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y));
        return new ExamSolution(ExamSelectedAnswers.of(examSelectedAnswerMap));
    }
}
