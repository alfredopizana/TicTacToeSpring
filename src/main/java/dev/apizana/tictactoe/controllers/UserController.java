package dev.apizana.tictactoe.controllers;

import dev.apizana.tictactoe.domain.dtos.UserDto;
import dev.apizana.tictactoe.domain.models.User;
import dev.apizana.tictactoe.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//@Controller
@RequestMapping("/users")
@RestController
@SecurityRequirement(name = "bearerAuth")
@Tags(value = {
        @Tag(name = "users")
})
public class UserController {

    @Autowired
    private UserService userService;


    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User Created",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "204",
                    description = "No users Found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not available",
                    content = @Content
            ),

    })
    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserDto user){
        if(user.getUsername() == null || user.getUsername().isBlank() )
            return new ResponseEntity(null,HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userService.create(user),HttpStatus.CREATED);
    }


    @Operation(summary = "Get all users stored in the DB" )
    @Tags(value = {
            @Tag(name = "users")
    })
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Users found on the DB",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "204",
                    description = "No users Found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not available",
                    content = @Content
            )
    })
    @GetMapping(value = "")
    public ResponseEntity<List<User>> getAll(){
            List<User> users = new ArrayList<>();
            userService.getAllUsers().forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<List<User>>(users,HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    @Tags(value = {
            @Tag(name = "users")
    })
    @GetMapping("/{username}")
    public  ResponseEntity<User> getByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.getByUsername(username),HttpStatus.CREATED);
    }


    @Tags(value = {
            @Tag(name = "users")
    })
    @PatchMapping("/{username}")
    public ResponseEntity<User> update(@PathVariable String username, @RequestBody UserDto user){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!authUser.getUsername().equals(username))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<User>(userService.update(username,user),HttpStatus.OK);
    }
    @Tags(value = {
            @Tag(name = "users")
    })
    @DeleteMapping("/{username}")
    public ResponseEntity<Boolean> delete(@PathVariable String username){

        return new ResponseEntity<>(userService.deleteByUsername(username),HttpStatus.ACCEPTED);
    }
}
