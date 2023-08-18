package io.mazurkiewicz.quizer.auth

import io.mazurkiewicz.quizer.auth.user.UserRepresentation
import org.springframework.security.core.Authentication

fun Authentication.toUserRepresentation(): UserRepresentation {
    return principal as UserRepresentation
}