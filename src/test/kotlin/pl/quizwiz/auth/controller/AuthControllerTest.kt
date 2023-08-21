package pl.quizwiz.auth.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import pl.quizwiz.auth.token.TokenService
import pl.quizwiz.auth.user.UserRepresentation
import pl.quizwiz.auth.user.UserService
import pl.quizwiz.exception.ApiError
import pl.quizwiz.exception.QuizerExceptionHandler
import java.util.*

class AuthControllerTest {

    private val tokenService = mockk<TokenService>()
    private val userService = mockk<UserService>()
    private val authController = AuthController(tokenService, userService)
    private val objectMapper = jacksonObjectMapper()
    private val mockMvc = MockMvcBuilders.standaloneSetup(authController)
        .setControllerAdvice(QuizerExceptionHandler())
        .build()

    @Test
    fun `should return token for new registered user`() {
        //given
        val email = "user@mail.local"
        val password = RandomStringUtils.randomAlphanumeric(8)
        val request = UserAuthRequest(
            email,
            password,
        )
        val token = RandomStringUtils.randomAlphanumeric(12)

        every { userService.createUser(email, password) } returns UserRepresentation(UUID.randomUUID(), email, true)
        every { tokenService.generateToken(any()) } returns token

        //when
        val response = mockMvc.perform(
            post("/api/auth/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk())
            .andReturn()
            .response

        //then
        verify(exactly = 1) { userService.createUser(any(), any()) }
        verify(exactly = 1) { tokenService.generateToken(any()) }
        val authResponse =
            objectMapper.readValue(response.contentAsString, LoginResponse::class.java)
        assertEquals(token, authResponse.token)
    }

    @Test
    fun `should return 400 when register email is already registered`() {
        //given
        val email = "test@mail.local"
        val password = RandomStringUtils.randomAlphanumeric(8)
        val request = UserAuthRequest(
            email,
            password,
        )
        every { userService.createUser(email, password) } throws pl.quizwiz.auth.EmailAlreadyExistException(email)

        //when
        val response = mockMvc.perform(
            post("/api/auth/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isBadRequest())
            .andReturn()
            .response

        //then
        verify(exactly = 1) { userService.createUser(any(), any()) }
        verify(exactly = 0) { tokenService.generateToken(any()) }
        val errorResponse =
            objectMapper.readValue(response.contentAsString, ApiError::class.java)
        assertEquals("M2AUTH_1001", errorResponse.errorCode)
        assertEquals("Email $email is already registered", errorResponse.message)

    }

    @Test
    fun `should return 400 when register email is not valid`() {
        //given
        val email = "invalidmail"
        val password = RandomStringUtils.randomAlphanumeric(8)
        val request = UserAuthRequest(
            email,
            password,
        )

        //when
        mockMvc.perform(
            post("/api/auth/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isBadRequest())

        //then
        verify(exactly = 0) { userService.createUser(any(), any()) }
        verify(exactly = 0) { tokenService.generateToken(any()) }
    }

    @Test
    fun `should return 400 when register email is blank`() {
        //given
        val email = ""
        val password = RandomStringUtils.randomAlphanumeric(8)
        val request = UserAuthRequest(
            email,
            password,
        )

        //when
        mockMvc.perform(
            post("/api/auth/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isBadRequest())

        //then
        verify(exactly = 0) { userService.createUser(any(), any()) }
        verify(exactly = 0) { tokenService.generateToken(any()) }
    }

    @Test
    fun `should return 400 when register password is too short`() {
        //given
        val email = "test@mail.local"
        val password = RandomStringUtils.randomAlphanumeric(7)
        val request = UserAuthRequest(
            email,
            password,
        )

        //when
        mockMvc.perform(
            post("/api/auth/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isBadRequest())

        //then
        verify(exactly = 0) { userService.createUser(any(), any()) }
        verify(exactly = 0) { tokenService.generateToken(any()) }
    }

    @Test
    fun `should return 400 when register password is too long`() {
        //given
        val email = "test@mail.local"
        val password = RandomStringUtils.randomAlphanumeric(65)
        val request = UserAuthRequest(
            email,
            password,
        )

        //when
        mockMvc.perform(
            post("/api/auth/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isBadRequest())

        //then
        verify(exactly = 0) { userService.createUser(any(), any()) }
        verify(exactly = 0) { tokenService.generateToken(any()) }
    }

    @Test
    fun `should return token for logged user`() {
        //given
        val email = "user@mail.local"
        val password = RandomStringUtils.randomAlphanumeric(8)
        val request = UserAuthRequest(
            email,
            password,
        )
        val token = RandomStringUtils.randomAlphanumeric(12)

        every { userService.findUserForCredentials(email, password) } returns UserRepresentation(
            UUID.randomUUID(),
            email,
            true
        )
        every { tokenService.generateToken(any()) } returns token

        //when
        val response = mockMvc.perform(
            post("/api/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk())
            .andReturn()
            .response

        //then
        verify(exactly = 1) { userService.findUserForCredentials(any(), any()) }
        verify(exactly = 1) { tokenService.generateToken(any()) }
        val authResponse =
            objectMapper.readValue(response.contentAsString, LoginResponse::class.java)
        assertEquals(token, authResponse.token)
    }

    @Test
    fun `should return 401 for invalid user credentials`() {
        //given
        val email = "user@mail.local"
        val password = RandomStringUtils.randomAlphanumeric(8)
        val request = UserAuthRequest(
            email,
            password,
        )
        val token = RandomStringUtils.randomAlphanumeric(12)

        every {
            userService.findUserForCredentials(
                email,
                password
            )
        } throws pl.quizwiz.auth.CredentialsNotValidException()
        every { tokenService.generateToken(any()) } returns token

        //when
        val response = mockMvc.perform(
            post("/api/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isUnauthorized())
            .andReturn()
            .response

        //then
        verify(exactly = 1) { userService.findUserForCredentials(any(), any()) }
        verify(exactly = 0) { tokenService.generateToken(any()) }
        val errorResponse =
            objectMapper.readValue(response.contentAsString, ApiError::class.java)
        assertEquals("M2AUTH_1002", errorResponse.errorCode)
        assertEquals("Incorrect email or password", errorResponse.message)
    }

}
