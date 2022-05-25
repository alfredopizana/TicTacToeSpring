package dev.apizana.tictactoe.services;

import dev.apizana.tictactoe.domain.dtos.UserDto;
import dev.apizana.tictactoe.domain.models.User;
import dev.apizana.tictactoe.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder bcryptEncoder;

    public User create(UserDto user)
    {
        if (userRepository.findByEmailOrUsername(user.getEmail(), user.getUsername()).isPresent())
            throw new ValidationException("User already exist in database");
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAllByActiveTrue();
    }

    public User getByUsername(String username) {
        Optional<User> user = userRepository.findByUsernameAndActiveTrue(username);
        if (user.isEmpty())
            return null;
        return user.get();
    }

    public User update(String username, UserDto user) {
        //User userFound = validateAndGetLoggedUserByUsername(username);
        //check if User and Email does not exist
        //Optional<User> userFound = );
        if (userRepository.findByEmailOrUsername(user.getEmail(), user.getUsername()).isPresent())
            throw new ValidationException("user already exist in database");
        Optional<User> userFound = userRepository.findByUsernameAndActiveTrue(username);
        if(userFound.isEmpty())
            throw new ValidationException("username not found");
        userFound.get().setUsername(user.getUsername());
        userFound.get().setEmail(user.getEmail());
        userFound.get().setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(userFound.get());
    }
    public Boolean deleteByUsername(String username) {
       Optional<User> user = userRepository.findByUsernameAndActiveTrue(username);
        if (user.isEmpty() || !user.get().getActive())
            return false;
        user.get().setActive(false);
        user.get().setModifiedDate(Instant.now());
        userRepository.save(user.get());
        return true;
    }
    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameAndActiveTrue(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found with username: " + username);
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                    new ArrayList<>());
    }
    /*
    public User validateAndGetLoggedUserByUsername(String username){
        UserDetails currentLoggedUser = loadUserByUsername(username);
        Optional<User> userFound = userRepository.findByUsernameAndActiveTrue(username);
        if (!currentLoggedUser.getUsername().equals(username))
            throw new SecurityException("Authentication Issue");
        if(userFound.isEmpty())
            throw new UsernameNotFoundException("User not found with username: " + username);
        return userFound.get();
    }*/

}
