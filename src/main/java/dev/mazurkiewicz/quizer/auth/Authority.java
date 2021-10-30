package dev.mazurkiewicz.quizer.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "authorities")
public class Authority implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String authority;

    @Override
    public String toString() {
        return authority;
    }
}
