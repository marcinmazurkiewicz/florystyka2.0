package dev.mazurkiewicz.florystyka.jwt;

import dev.mazurkiewicz.florystyka.auth.Authority;
import dev.mazurkiewicz.florystyka.exception.TokenExpiredException;
import dev.mazurkiewicz.florystyka.exception.UnauthorizedAccessException;
import dev.mazurkiewicz.florystyka.user.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final SecretKey secretKey;
    private final JwtProperties jwtProperties;
    private final Logger logger;

    public String generateToken(User user) {
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(jwtProperties.getIssuer())
                .claim("authorities", getUserAuthorities(user))
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(calculateExpirationTime())
                .signWith(secretKey)
                .compact();
        return String.format("%s %s", jwtProperties.getTokenPrefix(), token);
    }

    private Set<String> getUserAuthorities(User user) {
        return user.getAuthorities().stream().map(Authority::getAuthority).collect(Collectors.toSet());
    }

    private Timestamp calculateExpirationTime() {
        return Timestamp.valueOf(LocalDateTime.now().plusSeconds(jwtProperties.getTokenExpirationAfterSeconds()));
    }

    public Jws<Claims> getJwt(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        } catch (ExpiredJwtException ex) {
            throw new TokenExpiredException("Token has been expired");
        } catch (JwtException ex) {
            logger.error(String.format("Token cannot be trusted: %s", ex.getMessage()));
            throw new UnauthorizedAccessException(String.format("Token cannot be trusted: %s", ex));
        }
    }
}
