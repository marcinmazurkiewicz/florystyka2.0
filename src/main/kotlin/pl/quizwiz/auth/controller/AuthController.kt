package pl.quizwiz.auth.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.quizwiz.auth.token.TokenService
import pl.quizwiz.auth.user.UserService

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val tokenService: TokenService,
    private val userService: UserService
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: UserAuthRequest): LoginResponse {
        val user = userService.createUser(request.email, request.password)
        return LoginResponse(tokenService.generateToken(user))
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: UserAuthRequest): LoginResponse {
        val user = userService.findUserForCredentials(request.email, request.password)
        return LoginResponse(tokenService.generateToken(user))
    }
}

data class UserAuthRequest(
    @field:NotBlank @field:Email val email: String,
    @field:Size(min = 8, max = 64) val password: String,
)

data class LoginResponse(
    val token: String
)
