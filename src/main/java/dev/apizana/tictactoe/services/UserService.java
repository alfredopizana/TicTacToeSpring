package dev.apizana.tictactoe.services;

import dev.apizana.tictactoe.models.User;
import dev.apizana.tictactoe.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    public ResponseEntity<List<User>> getAllUsers(){

        try {
            List<User> users = new ArrayList<User>();
              userRepository.findAll().forEach(users::add);
            if (users.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);

          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
    public User getById(Long id){
        return userRepository.getById(id);
    }
    public User create(User user){
        return userRepository.save(user);
    }

    public User update(User user){
        
        return userRepository.save(user);
        //userRepository
    }
}
