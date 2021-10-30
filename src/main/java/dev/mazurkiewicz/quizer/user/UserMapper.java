package dev.mazurkiewicz.quizer.user;

import dev.mazurkiewicz.quizer.auth.Authority;
import dev.mazurkiewicz.quizer.auth.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User mapRequestToEntity(NewUserRequest userRequest) {
        User result = new User();
        result.setUsername(userRequest.getUsername().toLowerCase());
        result.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        result.setEnabled(true);
        Set<Authority> authorities = userRequest.roles == null || userRequest.roles.isEmpty()
                ? UserRole.STUDENT.getGrantedAuthorities()
                : userRequest.getRoles().stream()
                .map(UserRole::getGrantedAuthorities)
                .collect(HashSet::new, Set::addAll, Set::addAll);
        result.setAuthorities(authorities);
        return result;
    }

    public UserResponse mapEntityToResponse(User entity) {
        Set<String> authorities = entity.getAuthorities().stream().map(Authority::getAuthority).collect(Collectors.toSet());
        return new UserResponse(entity.getId(), entity.getUsername(), authorities);
    }
}
