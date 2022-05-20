package dev.apizana.tictactoe.services;

import dev.apizana.tictactoe.domain.dtos.UserDto;
import dev.apizana.tictactoe.domain.models.User;
import dev.apizana.tictactoe.repositories.UserRepository;
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

    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            return null;
        return user.get();
    }

    public UserDto update(String username, UserDto user) {
        User userFound = validateAndGetLoggedUserByUsername(username);
        userFound.setUsername(user.getUsername());
        userFound.setEmail(user.getEmail());
        userFound.setPassword(bcryptEncoder.encode(user.getPassword()));
        return new UserDto();
    }

    public Boolean deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            return false;

        user.get().setActive(false);
        user.get().setModifiedDate(Instant.now());
        userRepository.save(user.get());
        return true;
    }
    public Boolean deleteByUsername(String username) {
        User user = validateAndGetLoggedUserByUsername(username);
        if (!user.getActive())
            return false;
        user.setActive(false);
        user.setModifiedDate(Instant.now());
        userRepository.save(user);
        return true;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found with username: " + username);
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                    new ArrayList<>());
    }
    public User validateAndGetLoggedUserByUsername(String username){
        UserDetails currentLoggedUser = loadUserByUsername(username);
        Optional<User> userFound = userRepository.findByUsername(username);
        if (!currentLoggedUser.getUsername().equals(username))
            throw new SecurityException("Authentication Issue");
        if(userFound.isEmpty())
            throw new UsernameNotFoundException("User not found with username: " + username);
        return userFound.get();
    }
    /*
    public User save(UserDto user) {
        User newUser = new User();
        String userPassword = user.getPassword();
        String userEmail = user.getUsername();
        newUser.setUsername(userEmail);
        newUser.setPassword(bcryptEncoder.encode(userPassword));
        newUser.setEmail("testserver@email.com");
        return userRepository.save(newUser);
    }*/
}
