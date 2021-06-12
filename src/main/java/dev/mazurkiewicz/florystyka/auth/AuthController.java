package dev.mazurkiewicz.florystyka.auth;

import dev.mazurkiewicz.florystyka.config.SecurityProperties;
import dev.mazurkiewicz.florystyka.exception.TokenExpiredException;
import dev.mazurkiewicz.florystyka.jwt.JwtTokenUtil;
import dev.mazurkiewicz.florystyka.user.User;
import dev.mazurkiewicz.florystyka.user.UserMapper;
import dev.mazurkiewicz.florystyka.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("/api/v3/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserMapper userMapper;
    private final SecurityProperties securityProperties;
    private final Logger logger;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = (User) authentication.getPrincipal();
        ResponseEntity<?> response = prepareResponseWithTokens(user);
        logger.info(String.format("User %d has logged in at %2$tY-%2$tm-%2$td %2$tH:%2$tM:%2$tS", user.getId(), new Date()));
        return response;
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue(name="refresh-token") String refreshTokenCookie) {
        RefreshToken refreshToken = refreshTokenService.getToken(refreshTokenCookie);
        if (refreshToken.getExpiredAt().isBefore(Instant.now())) {
            refreshTokenService.removeRefreshToken(refreshToken);
            throw new TokenExpiredException("Refresh token has expired");
        }
        User loggedUser = userService.getUserById(refreshToken.getUserId());
        refreshTokenService.removeRefreshToken(refreshToken);
        return prepareResponseWithTokens(loggedUser);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(name="refresh-token") String refreshTokenCookie) {
        RefreshToken refreshToken = refreshTokenService.getToken(refreshTokenCookie);
        refreshTokenService.removeRefreshToken(refreshToken);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<?> prepareResponseWithTokens(User user) {
        ResponseCookie refreshTokenCookie = ResponseCookie.from(securityProperties.getRefreshTokenHeader(),
                refreshTokenService.createNewRefreshToken(user.getId()))
                .httpOnly(true)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateToken(user))
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(userMapper.mapEntityToResponse(user));
    }
}
