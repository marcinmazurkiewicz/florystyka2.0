package dev.mazurkiewicz.quizer.exam.domain.port;

import dev.mazurkiewicz.quizer.exam.domain.model.Exam;
import dev.mazurkiewicz.quizer.exam.domain.model.ExamDuration;
import dev.mazurkiewicz.quizer.exam.domain.model.ExamPassThreshold;
import dev.mazurkiewicz.quizer.question.domain.model.Question;
import dev.mazurkiewicz.quizer.question.domain.port.QuestionRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ExamService {

    private final QuestionRepository questionRepository;

    public Exam generateExam(QuestionNumber questionNumber, ExamDuration duration, ExamPassThreshold passThreshold) {
        List<Question> randomQuestions = questionRepository.getRandomQuestions(questionNumber);
        return new Exam(randomQuestions, duration, passThreshold);
    }
}
