package dev.apizana.tictactoe.repositories;

import dev.apizana.tictactoe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {

}
