package dev.apizana.tictactoe.controllers;

import dev.apizana.tictactoe.models.User;
import dev.apizana.tictactoe.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Controller
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users stored in the DB" )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Users found on the DB",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not available",
                    content = @Content
            )
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll(){
        //List<User> users = userRepository.findAll();
        //User aUser = new User(1L,"user@email.com","pasword","1",true, Instant.now(),Instant.now());
        return userService.getAllUsers();
        /*return new ArrayList<User>(){
            {
                add(new User());
                add(new User());
            }
        };*/
    }
    /*@RequestMapping("/{id}")
    public User getBbyId(@PathVariable("id") User user, Model model){

    }*/
    @GetMapping("/{id}")
    public User getById(@PathVariable("id") User user, Model model){
        return new User();
    }
    @PostMapping
    public User create(@RequestBody User user){
        //if(user.getActive() == null)

        return userService.create(user);
    }

    @PatchMapping
    public User update(){
        return new User();
    }

    @DeleteMapping
    public User delete(){
        return new User();
    }
}
