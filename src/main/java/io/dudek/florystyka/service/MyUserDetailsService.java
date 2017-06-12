package io.dudek.florystyka.service;

import io.dudek.florystyka.domain.User;
import io.dudek.florystyka.repository.UserRepository;
import io.dudek.florystyka.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByMail(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new MyUserDetails(user);
    }
}
