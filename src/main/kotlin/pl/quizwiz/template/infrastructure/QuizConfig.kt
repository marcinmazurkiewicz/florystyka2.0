package pl.quizwiz.template.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.quizwiz.template.domain.port.QuizTemplateRepository
import pl.quizwiz.template.domain.port.QuizTemplateService

@Configuration
class QuizConfig {

    @Bean
    fun quizTemplateService(quizTemplateRepository: QuizTemplateRepository) =
        QuizTemplateService(quizTemplateRepository)
}