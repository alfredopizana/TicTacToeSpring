package dev.apizana.tictactoe.services;

import dev.apizana.tictactoe.domain.dtos.GameDto;
import dev.apizana.tictactoe.domain.dtos.MovementDto;
import dev.apizana.tictactoe.domain.models.Game;
import dev.apizana.tictactoe.domain.models.Movement;
import dev.apizana.tictactoe.repositories.GameRepository;
import dev.apizana.tictactoe.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    public Game create(GameDto gameDto){
        Game newGame = new Game();

        if(gameDto.getCreator() != null){
            //find if creator Exists
            if(userRepository.findByUsernameAndActiveTrue(gameDto.getCreator()).isEmpty())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Creator not found");
            newGame.setCreator(gameDto.getCreator());
        }
        if(gameDto.getGameMode() != null) newGame.setGameMode(gameDto.getGameMode());
        if(gameDto.getGameStatus() != null) newGame.setGameStatus(gameDto.getGameStatus());
        if(gameDto.getWinner() != null) newGame.setWinner(gameDto.getWinner());
        return gameRepository.save(newGame);
    }
    public Game generateMovement(Long gameId, MovementDto movementDto){
        Optional<Game> gameFound = gameRepository.findById(gameId);
        if(gameFound.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Game does not exist");
        Game game = gameFound.get();
        Movement newMovement = new Movement();
        newMovement.setMovementNumber(movementDto.getMovementNumber());
        newMovement.setPosition(movementDto.getPosition());
        newMovement.setSymbol(movementDto.getSymbol());
        game.getHistory().add(newMovement);

        return gameRepository.save(game)    ;
    }

    public List<Game> getAll(){
        return gameRepository.findAll();
    }

    public List<Game> getAllByUsername(String username){
        return gameRepository.findAllByCreator(username);
    }

    public Boolean deleteById(Long id){
        if(gameRepository.findById(id).isEmpty())
            return false;
        gameRepository.deleteById(id);
        return true;
    }
}
