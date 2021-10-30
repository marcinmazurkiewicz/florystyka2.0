package dev.mazurkiewicz.quizer.user;

import dev.mazurkiewicz.quizer.auth.Authority;
import dev.mazurkiewicz.quizer.auth.AuthorityService;
import dev.mazurkiewicz.quizer.auth.UserAuthHelper;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
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
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
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
        LoggedUserDetails userDetails = userAuthHelper.getLoggedUser();
        Set<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return new UserResponse(userDetails.getId(), userDetails.getUsername(), authorities);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %d doesn't exist", id)));
    }
}
