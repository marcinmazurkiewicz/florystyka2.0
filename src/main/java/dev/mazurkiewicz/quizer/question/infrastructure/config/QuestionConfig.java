package dev.mazurkiewicz.quizer.question.infrastructure.config;

import dev.mazurkiewicz.quizer.question.domain.port.QuestionRepository;
import dev.mazurkiewicz.quizer.question.domain.port.QuestionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class QuestionConfig {

    @Bean
    QuestionService questionService(final QuestionRepository repository) {
        return new QuestionService(repository);
    }
}
