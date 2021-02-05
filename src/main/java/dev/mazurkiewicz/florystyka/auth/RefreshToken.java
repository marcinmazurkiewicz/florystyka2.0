package dev.mazurkiewicz.florystyka.auth;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
public class RefreshToken {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String refreshToken;
    private Instant createdAt;
    private Instant expiredAt;

}
