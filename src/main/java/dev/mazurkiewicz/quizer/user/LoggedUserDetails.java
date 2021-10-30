package dev.mazurkiewicz.quizer.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public class LoggedUserDetails {

    private Long id;
    private String username;
    private Set<? extends GrantedAuthority> authorities;

    public LoggedUserDetails(Long id, String username, Set<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getUsername() {
        return username;
    }
}
