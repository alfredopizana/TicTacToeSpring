package dev.apizana.tictactoe.repositories;

import dev.apizana.tictactoe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IUserRepository
        extends JpaRepository<User, Long> {

}
