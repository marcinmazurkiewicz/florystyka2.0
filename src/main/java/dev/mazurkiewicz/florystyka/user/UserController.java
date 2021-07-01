package dev.mazurkiewicz.florystyka.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v3/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/me")
    public UserResponse getLoggedUser() {
        return userService.getLoggedUserDetails();
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody @Valid NewUserRequest userRequest) {
        Long savedUserId = userService.registerUser(userRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUserId).toUri();
        return ResponseEntity.created(location).build();
    }
}
