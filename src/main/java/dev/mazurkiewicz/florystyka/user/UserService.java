package dev.mazurkiewicz.florystyka.user;

import dev.mazurkiewicz.florystyka.auth.Authority;
import dev.mazurkiewicz.florystyka.auth.AuthorityService;
import dev.mazurkiewicz.florystyka.auth.UserAuthHelper;
import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import dev.mazurkiewicz.florystyka.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityService authorityService;
    private final UserAuthHelper userAuthHelper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.selectUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with login %s not found", username)));
    }

    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    public Long registerUser(NewUserRequest userRequest) {
        User toRegister = userMapper.mapRequestToEntity(userRequest);
        Set<Authority> authorities = toRegister.getAuthorities().stream()
                .map(authorityService::findInDatabaseOrSave)
                .collect(Collectors.toSet());
        toRegister.setAuthorities(authorities);

        return userRepository.save(toRegister).getId();
    }

    public UserResponse getLoggedUserDetails() {
        long loggedUserId = userAuthHelper.geLoggedUserId();
        User loggedUser = userRepository.findById(loggedUserId)
                .orElseThrow(() -> new UnauthorizedAccessException(String.format("User with id %d doesn't exist", loggedUserId)));
        return userMapper.mapEntityToResponse(loggedUser);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %d doesn't exist", id)));
    }
}
