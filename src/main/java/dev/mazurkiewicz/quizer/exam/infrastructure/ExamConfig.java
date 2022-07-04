package dev.mazurkiewicz.quizer.exam.infrastructure;

import dev.mazurkiewicz.quizer.exam.domain.port.ExamService;
import dev.mazurkiewicz.quizer.question.domain.port.QuestionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExamConfig {

    @Bean
    public ExamService examService(QuestionRepository questionRepository) {
        return new ExamService(questionRepository);
    }
}
