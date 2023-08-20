package io.mazurkiewicz.quizer.quiz.infrastructure

import io.mazurkiewicz.quizer.quiz.domain.port.QuizTemplateRepository
import io.mazurkiewicz.quizer.quiz.domain.port.QuizTemplateService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuizConfig {

    @Bean
    fun quizTemplateService(quizTemplateRepository: QuizTemplateRepository) =
        QuizTemplateService(quizTemplateRepository)
}