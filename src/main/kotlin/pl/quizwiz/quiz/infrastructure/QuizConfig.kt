package pl.quizwiz.quiz.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.quizwiz.quiz.domain.port.QuizTemplateRepository
import pl.quizwiz.quiz.domain.port.QuizTemplateService

@Configuration
class QuizConfig {

    @Bean
    fun quizTemplateService(quizTemplateRepository: QuizTemplateRepository) =
        QuizTemplateService(quizTemplateRepository)
}