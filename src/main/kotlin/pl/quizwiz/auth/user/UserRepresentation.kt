package pl.quizwiz.auth.user

import java.util.*

data class UserRepresentation(
    val id: UUID,
    val email: String,
    val enabled: Boolean
) {

    constructor(user: User) : this(user.userPublicId, user.email, user.enabled)
}