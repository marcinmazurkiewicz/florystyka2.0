package io.mazurkiewicz.quizer.auth.token

import io.mazurkiewicz.quizer.auth.config.JwtProperties
import io.mazurkiewicz.quizer.auth.user.UserRepresentation
import io.mazurkiewicz.quizer.auth.user.UserService
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class TokenService(
    private val jwtEncoder: JwtEncoder,
    private val jwtDecoder: JwtDecoder,
    private val userService: UserService,
    private val jwtProperties: JwtProperties
) {
    private val userIdClaimName = "userId"

    fun generateToken(user: UserRepresentation): String {
        val jwsHeader = JwsHeader.with { jwtProperties.tokenAlgorithm }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(jwtProperties.expirationInSeconds))
            .subject(user.email)
            .claim(userIdClaimName, user.id)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): UserRepresentation {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims[userIdClaimName] as String
            val userPublicId = UUID.fromString(userId)
            userService.findByPublicId(userPublicId)
        } catch (e: JwtException) {
            throw InvalidBearerTokenException(e.localizedMessage)
        }
    }
}