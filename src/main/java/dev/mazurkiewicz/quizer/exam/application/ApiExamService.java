package dev.mazurkiewicz.quizer.exam.application;

import dev.mazurkiewicz.quizer.config.QuizerConfiguration;
import dev.mazurkiewicz.quizer.exam.domain.model.Exam;
import dev.mazurkiewicz.quizer.exam.domain.model.ExamDuration;
import dev.mazurkiewicz.quizer.exam.domain.model.ExamPassThreshold;
import dev.mazurkiewicz.quizer.exam.domain.port.ExamService;
import dev.mazurkiewicz.quizer.exam.domain.port.QuestionNumber;
import dev.mazurkiewicz.quizer.question.application.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class ApiExamService {

    private final ExamService service;
    private final QuizerConfiguration configuration;


    ExamResponse getExam() {
        Exam exam = service.generateExam(QuestionNumber.of(configuration.examQuestionsNumber()),
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
}
