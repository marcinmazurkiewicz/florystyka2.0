package dev.mazurkiewicz.florystyka.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM users u WHERE LOWER(u.email) = LOWER(:username)")
    Optional<User> selectUserByUsername(String username);

    Optional<User> findByEmail(String email);

}
