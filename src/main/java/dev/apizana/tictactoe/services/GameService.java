package dev.apizana.tictactoe.services;

import dev.apizana.tictactoe.domain.dtos.GameDto;
import dev.apizana.tictactoe.domain.dtos.MovementDto;
import dev.apizana.tictactoe.domain.models.Game;
import dev.apizana.tictactoe.domain.models.GameStatus;
import dev.apizana.tictactoe.domain.models.Movement;
import dev.apizana.tictactoe.domain.models.MovementSymbol;
import dev.apizana.tictactoe.repositories.GameRepository;
import dev.apizana.tictactoe.repositories.UserRepository;
import dev.apizana.tictactoe.utils.Minimax;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Arrays;
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
        return gameRepository.save(newGame);
    }
    public Game generateMovement(Long gameId, MovementDto movementDto){
        Optional<Game> gameFound = gameRepository.findById(gameId);
        if(gameFound.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Game does not exist");
        Game game = gameFound.get();
        if(!game.getWinner().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Game already has a winner");
        if(game.getHistory().stream().filter(movement -> movementDto.getPosition() == movement.getPosition()).count() > 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Position already taken");
        Movement newMovement = new Movement();
        newMovement.setMovementNumber(game.getHistory().size());
        newMovement.setPosition(movementDto.getPosition());
        newMovement.setSymbol(movementDto.getSymbol());
        game.getHistory().add(newMovement);

        if(game.getGameStatus() == GameStatus.notstarted)
            game.setGameStatus(GameStatus.inprogress);

        // Generate an array board to check the winner
        MovementSymbol[] generatedBoard = new MovementSymbol[9];
        // Init Array with empty values
        Arrays.fill(generatedBoard, MovementSymbol.none);
        // Filling the data current movements
        game.getHistory().forEach(movement -> generatedBoard[movement.getPosition()] = movement.getSymbol());

        //check for winner
        if(Minimax.checkForWinner(generatedBoard,movementDto.getSymbol()) != null) {
            game.setWinner(movementDto.getSymbol().toString());
            game.setGameStatus(GameStatus.completed);
        }
        game.setModifiedDate(Instant.now());
        return gameRepository.save(game);
    }

    public Game generatePairOfMovements(Long gameId, MovementDto userMovementDto){

        Game currentGameState = this.generateMovement(gameId, userMovementDto);


        if(!currentGameState.getWinner().isEmpty() || currentGameState.getHistory().size() > 8) {
            return currentGameState;
        }

        // Movement of the AI
        MovementSymbol[] generatedBoard = new MovementSymbol[9];
        // Init Array with empty values
        Arrays.fill(generatedBoard, MovementSymbol.none);
        // Filling the data current movements
        currentGameState.getHistory().forEach(movement -> generatedBoard[movement.getPosition()] = movement.getSymbol());

        MovementDto aiMovement;

        if(userMovementDto.getSymbol() == MovementSymbol.cross)
            aiMovement = Minimax.randomMove(generatedBoard,MovementSymbol.circle);
        else
            aiMovement = Minimax.randomMove(generatedBoard,MovementSymbol.cross);


        return this.generateMovement(gameId, aiMovement);

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
