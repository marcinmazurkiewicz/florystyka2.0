package pl.quizwiz.auth.config

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class HashService {

    fun check(input: String, hash: String) = BCrypt.checkpw(input, hash)

    fun hash(input: String): String = BCrypt.hashpw(input, BCrypt.gensalt(10))
}