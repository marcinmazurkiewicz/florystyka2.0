package dev.mazurkiewicz.florystyka.auth;

import dev.mazurkiewicz.florystyka.config.SecurityProperties;
import dev.mazurkiewicz.florystyka.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;
    private final SecurityProperties securityProperties;

    public String createNewRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(userId);
        refreshToken.setRefreshToken(generateRefreshToken());
        refreshToken.setCreatedAt(Instant.now());
        refreshToken.setExpiredAt(LocalDateTime.now()
                .plusSeconds(securityProperties.getRefreshTokenExpirationAfterSeconds())
                .atZone(securityProperties.getTimezoneId()).toInstant());
        repository.save(refreshToken);
        return refreshToken.getRefreshToken();
    }

    public RefreshToken getToken(RefreshTokenRequest refreshToken) {
        return repository.getByRefreshToken(refreshToken.getRefreshToken())
                .orElseThrow(() ->
                        new UnauthorizedAccessException(String.format("Refresh token %s has not found", refreshToken)));
    }

    public void removeRefreshToken(RefreshToken refreshToken) {
        repository.delete(refreshToken);
    }

    private String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }
}
