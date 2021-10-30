package dev.mazurkiewicz.quizer.auth;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

import static dev.mazurkiewicz.quizer.auth.UserPermission.*;


public enum UserRole {
    ADMIN(Sets.newHashSet(UserPermission.values())),
    MODERATOR(Sets.newHashSet(QUESTION_READ, QUESTION_WRITE, SOLUTION_READ, TEST_READ, TEST_WRITE)),
    TEACHER(Sets.newHashSet(QUESTION_READ, SOLUTION_READ, TEST_READ, TEST_WRITE)),
    STUDENT(Sets.newHashSet(QUESTION_READ, SOLUTION_READ, SOLUTION_WRITE, TEST_READ));


    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<Authority> getGrantedAuthorities() {
        Set<Authority> permissions = getPermissions().stream()
                .map(permission -> new Authority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new Authority("ROLE_" + this.name()));
        return permissions;
    }
}
