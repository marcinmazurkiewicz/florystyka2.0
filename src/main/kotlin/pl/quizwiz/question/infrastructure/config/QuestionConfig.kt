package pl.quizwiz.question.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.quizwiz.question.domain.port.QuestionRepository
import pl.quizwiz.question.domain.port.QuestionService
import pl.quizwiz.quiz.domain.port.QuizTemplateService

@Configuration
class QuestionConfig {

    @Bean
    fun questionService(repository: QuestionRepository, templateService: QuizTemplateService) =
        QuestionService(repository, templateService)
}