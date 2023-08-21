package io.mazurkiewicz.quizer.question.infrastructure.config

import io.mazurkiewicz.quizer.question.domain.port.QuestionRepository
import io.mazurkiewicz.quizer.question.domain.port.QuestionService
import io.mazurkiewicz.quizer.quiz.domain.port.QuizTemplateService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuestionConfig {

    @Bean
    fun questionService(repository: QuestionRepository, templateService: QuizTemplateService) = QuestionService(repository, templateService)
}