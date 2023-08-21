package pl.quizwiz.auth.user

import jakarta.persistence.*
import java.util.*

@Entity(name = "users")
class User(
    var userPublicId: UUID,
    var email: String,
    var password: String,
    val enabled: Boolean,
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    var id: Long? = null
)