package io.mazurkiewicz.quizer.quiz.infrastructure

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface QuizTemplateMongoRepository : MongoRepository<TemplateMongoEntity, UUID> {
}