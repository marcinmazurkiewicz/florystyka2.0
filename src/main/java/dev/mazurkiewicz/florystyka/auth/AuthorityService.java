package dev.mazurkiewicz.florystyka.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    public Authority findInDatabaseOrSave(Authority authority) {
        return authorityRepository.findByAuthority(authority.getAuthority())
                .orElseGet(() -> save(authority));
    }
}
