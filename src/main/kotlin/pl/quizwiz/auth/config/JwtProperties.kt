package pl.quizwiz.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "auth.jwt")
data class JwtProperties(
    val secretKey: String,
    val expirationInSeconds: Long,
    val refreshExpirationInSeconds: Long,
    val tokenAlgorithm: String,
    val keyAlgorithm: String
)