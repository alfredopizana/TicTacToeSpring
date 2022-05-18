package dev.apizana.tictactoe.repositories;

import dev.apizana.tictactoe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
    List<User> findAllByActiveTrue();
    User findByEmailOrUsername(String email, String username);

    User findByUsername(String username);
}
