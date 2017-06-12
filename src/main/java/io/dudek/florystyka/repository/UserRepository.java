package io.dudek.florystyka.repository;

import io.dudek.florystyka.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByMail(String mail);
    User save(User user);

}
