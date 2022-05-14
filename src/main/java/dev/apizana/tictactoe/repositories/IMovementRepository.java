package dev.apizana.tictactoe.repositories;

import dev.apizana.tictactoe.models.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovementRepository extends JpaRepository<Movement,Integer> {
}
