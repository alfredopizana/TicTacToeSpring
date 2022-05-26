package dev.apizana.tictactoe.controllers;

import dev.apizana.tictactoe.domain.dtos.GameDto;
import dev.apizana.tictactoe.domain.dtos.MovementDto;
import dev.apizana.tictactoe.domain.models.Game;
import dev.apizana.tictactoe.domain.models.Movement;
import dev.apizana.tictactoe.services.GameService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/games")
@RestController
@SecurityRequirement(name = "bearerAuth")
@Tags(value = {
        @Tag(name = "games")
})
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping(value = "")
    ResponseEntity<Game> createNewGame(GameDto gameDto){
        return new ResponseEntity<Game>(gameService.create(gameDto),HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    ResponseEntity<List<Game>> getAll(){
        List<Game> gamesFound = new ArrayList<>();
                gameService.getAll().forEach(gamesFound::add);
        return new ResponseEntity<List<Game>>( gameService.getAll(),HttpStatus.OK);
    }
    @GetMapping(value = "/username/{username}")
    ResponseEntity<List<Game>> getAllByUsername(String username){
        return new ResponseEntity<List<Game>>(gameService.getAllByUsername(username),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Boolean> deleteGame(Long id){
        Boolean userDeletedStatus = gameService.deleteById(id);
        return new ResponseEntity<>(userDeletedStatus,HttpStatus.ACCEPTED);
    }

    //Movements
    @PostMapping(value = "/movements/{gameId}")
    ResponseEntity<Game> createMovement(@PathVariable Long gameId, @RequestBody MovementDto movementDto){
        return new ResponseEntity<>(gameService.generateMovement(gameId,movementDto),HttpStatus.OK);
    }



}
