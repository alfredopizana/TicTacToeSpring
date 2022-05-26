package dev.apizana.tictactoe.repositories;

import dev.apizana.tictactoe.domain.models.Game;
import dev.apizana.tictactoe.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>  {
    List<Game> findAllByCreator(String username);

}
