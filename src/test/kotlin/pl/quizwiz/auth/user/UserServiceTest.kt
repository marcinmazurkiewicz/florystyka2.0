package pl.quizwiz.auth.user

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.security.core.userdetails.UsernameNotFoundException
import pl.quizwiz.auth.CredentialsNotValidException
import pl.quizwiz.auth.EmailAlreadyExistException
import pl.quizwiz.auth.config.HashService
import java.util.*

class UserServiceTest {
    private val userRepository = mockk<UserRepository>()
    private val hashService = HashService()
    private val userService = UserService(userRepository, hashService)

    @Test
    fun `should return user representation for existing user`() {
        //given
        val userPublicId = UUID.randomUUID()
        val email = "test@mail.local"
        every { userRepository.findByUserPublicId(userPublicId) } returns User(
            userPublicId,
            email,
            "password",
            true,
            1L
        )

        //when
        val userRepresentation = userService.findByPublicId(userPublicId)

        //then
        verify(exactly = 1) { userRepository.findByUserPublicId(userPublicId) }
        assertThat(userRepresentation.id).isEqualTo(userPublicId)
        assertThat(userRepresentation.email).isEqualTo(email)
    }

    @Test
    fun `should throw exception when user does not exist`() {
        //given
        val userPublicId = UUID.randomUUID()
        every { userRepository.findByUserPublicId(userPublicId) } returns null

        //when
        val call: () -> Unit = { userService.findByPublicId(userPublicId) }

        //then
        assertThrows<UsernameNotFoundException>(call)
            .apply { assertThat(message).isEqualTo("User $userPublicId not found") }
        verify(exactly = 1) { userRepository.findByUserPublicId(userPublicId) }
    }

    @Test
    fun `should return save user to db`() {
        //given
        val email = "test@mail.local"
        val password = "password"
        every { userRepository.findByEmail(email) } returns null
        every { userRepository.save(any()) } returns User(UUID.randomUUID(), email, password, true, 1L)

        //when
        val userRepresentation = userService.createUser(email, password)

        //then
        verify(exactly = 1) { userRepository.findByEmail(email) }
        verify(exactly = 1) { userRepository.save(any()) }
        assertThat(userRepresentation.email).isEqualTo(email)
    }

    @Test
    fun `should throw exception during create user when user with email already exist`() {
        //given
        val email = "test@mail.local"
        every { userRepository.findByEmail(email) } returns User(UUID.randomUUID(), email, "password", true, 1L)

        //when
        val call: () -> Unit = { userService.createUser(email, "passowrd") }

        //then
        assertThrows<EmailAlreadyExistException>(call)
            .apply { assertThat(message).isEqualTo("Name already exists") }
        verify(exactly = 1) { userRepository.findByEmail(email) }
    }

    @Test
    fun `should return user for valid email and password`() {
        //given
        val email = "test@mail.local"
        val password = "password"
        val hashedPassword = hashService.hash(password)
        val userPublicId = UUID.randomUUID()
        every { userRepository.findByEmail(email) } returns User(userPublicId, email, hashedPassword, true, 1L)
        //when
        val userRepresentation = userService.findUserForCredentials(email, password)

        //then
        verify(exactly = 1) { userRepository.findByEmail(email) }
        assertThat(userRepresentation.id).isEqualTo(userPublicId)
        assertThat(userRepresentation.email).isEqualTo(email)
    }

    @Test
    fun `should throw exception for valid email and invalid password`() {
        //given
        val email = "test@mail.local"
        val password = "password"
        val hashedPassword = hashService.hash(password)
        every { userRepository.findByEmail(email) } returns User(UUID.randomUUID(), email, hashedPassword, true, 1L)

        //when
        val call: () -> Unit = { userService.findUserForCredentials(email, "wrongPassword") }

        //then
        assertThrows<CredentialsNotValidException>(call)
            .apply { assertThat(message).isEqualTo("Login failed") }
        verify(exactly = 1) { userRepository.findByEmail(email) }
    }

    @Test
    fun `should throw exception for invalid email`() {
        //given
        val email = "test@mail.local"
        val password = "password"
        every { userRepository.findByEmail(email) } returns null

        //when
        val call: () -> Unit = { userService.findUserForCredentials(email, password) }

        //then
        assertThrows<CredentialsNotValidException>(call)
            .apply { assertThat(message).isEqualTo("Login failed") }
        verify(exactly = 1) { userRepository.findByEmail(email) }
    }
}

