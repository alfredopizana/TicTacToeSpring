package dev.apizana.tictactoe.controllers;

import dev.apizana.tictactoe.domain.dtos.UserDto;
import dev.apizana.tictactoe.models.User;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
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
        //if(user.getActive() == null)
        //    return new ResponseEntity(null,HttpStatus.BAD_REQUEST);

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

        try {
            List<User> users = new ArrayList<>();
            userService.getAllUsers().forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @Tags(value = {
            @Tag(name = "users")
    })
    @GetMapping("/{id}")
    public  ResponseEntity<User> getById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getById(id),HttpStatus.CREATED);
    }


    @Tags(value = {
            @Tag(name = "users")
    })
    @PatchMapping
    public User update(){
        return new User();
    }
    @Tags(value = {
            @Tag(name = "users")
    })
    @DeleteMapping
    public ResponseEntity<Boolean> delete(Long id){

        return new ResponseEntity<>(userService.deleteById(id),HttpStatus.ACCEPTED);
    }
}
