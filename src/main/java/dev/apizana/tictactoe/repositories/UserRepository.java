package dev.apizana.tictactoe.repositories;

import dev.apizana.tictactoe.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
    List<User> findAllByActiveTrue();
    Optional<User> findByEmailOrUsername(String email, String username);

    Optional<User> findByUsername(String username);

}
