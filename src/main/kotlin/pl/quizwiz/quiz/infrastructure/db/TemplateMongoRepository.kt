package pl.quizwiz.quiz.infrastructure.db

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface TemplateMongoRepository : MongoRepository<TemplateMongoEntity, UUID> {
}