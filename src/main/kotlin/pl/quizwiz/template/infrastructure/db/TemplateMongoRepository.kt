package pl.quizwiz.template.infrastructure.db

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface TemplateMongoRepository : MongoRepository<TemplateMongoEntity, UUID> {
}