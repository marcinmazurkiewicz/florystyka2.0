package dev.mazurkiewicz.florystyka.user;

import dev.mazurkiewicz.florystyka.auth.Authority;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity(name = "users")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean isAccountExpired;
    private boolean isAccountLocked;
    private boolean isCredentialsExpired;
    private boolean isEnabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "auth_id", referencedColumnName = "id")})
    private Set<Authority> authorities;

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return !isAccountExpired;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return !isAccountLocked;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired;
    }
}
