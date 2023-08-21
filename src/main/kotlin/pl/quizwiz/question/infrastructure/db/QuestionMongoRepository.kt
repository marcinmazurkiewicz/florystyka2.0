package pl.quizwiz.question.infrastructure.db

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface QuestionMongoRepository : MongoRepository<QuestionMongoEntity, UUID>