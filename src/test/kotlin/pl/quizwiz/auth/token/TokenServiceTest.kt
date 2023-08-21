package pl.quizwiz.auth.token

import org.apache.commons.lang3.RandomStringUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import pl.quizwiz.auth.config.JwtProperties
import pl.quizwiz.auth.user.User
import pl.quizwiz.auth.user.UserRepository
import pl.quizwiz.auth.user.UserRepresentation
import java.time.Instant
import java.util.*

@SpringBootTest
class TokenServiceTest(
    @Autowired private val tokenService: TokenService,
    @Autowired private val jwtDecoder: JwtDecoder,
    @Autowired private val jwtProperties: JwtProperties,
    @Autowired private val userRepository: UserRepository
) {

    @Test
    fun `should generate new token`() {
        //given
        val publicUserId = UUID.randomUUID()
        val email = generateEmail()
        val user = UserRepresentation(publicUserId, email, true)

        //when
        val token = tokenService.generateToken(user)

        //then
        val decodedToken = jwtDecoder.decode(token)
        assertThat(decodedToken.claims["userId"])
            .isEqualTo(publicUserId.toString())
        assertThat(decodedToken.claims["sub"])
            .isEqualTo(email)
        val expirationTime = decodedToken.claims["exp"] as Instant
        assertThat(expirationTime)
            .isBetween(
                Instant.now().plusSeconds(jwtProperties.expirationInSeconds).minusSeconds(3),
                Instant.now().plusSeconds(jwtProperties.expirationInSeconds)
            )
    }

    @Test
    fun `should return user representation for valid token`() {
        //given
        val publicUserId = UUID.randomUUID()
        val email = generateEmail()
        val user = UserRepresentation(publicUserId, email, true)
        val token = tokenService.generateToken(user)
        userRepository.save(User(publicUserId, email, "password", true))

        //when
        val userRepresentation = tokenService.parseToken(token)

        //then
        assertThat(userRepresentation.email).isEqualTo(email)
        assertThat(userRepresentation.id).isEqualTo(publicUserId)
    }

    @Test
    fun `should throw exception when not found user for token`() {
        //given
        val publicUserId = UUID.randomUUID()
        val email = generateEmail()
        val user = UserRepresentation(publicUserId, email, true)
        val token = tokenService.generateToken(user)

        //when
        val block: () -> Unit = { tokenService.parseToken(token) }

        //then
        assertThrows<UsernameNotFoundException>(block)
    }

    @Test
    fun `should throw exception when token is not valid`() {
        //given
        val token = RandomStringUtils.randomAlphanumeric(64)

        //when
        val block: () -> Unit = { tokenService.parseToken(token) }

        //then
        assertThrows<InvalidBearerTokenException>(block)
    }

    private fun generateEmail(): String = "${RandomStringUtils.randomAlphabetic(10)}@mail.local"
}
