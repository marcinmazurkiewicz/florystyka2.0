package pl.quizwiz.auth

import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import pl.quizwiz.auth.user.UserRepresentation
import java.util.*


fun prepareAuthentication(
    userId: UUID = UUID.randomUUID(),
    email: String = "user@mail.local"
): HandlerMethodArgumentResolver {
    return AuthenticationResolver(userId, email)
}


class AuthenticationResolver(private val userId: UUID, private val email: String) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType.isAssignableFrom(UserRepresentation::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        return UserRepresentation(userId, email, true)
    }

}

