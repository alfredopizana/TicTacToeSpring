package dev.apizana.tictactoe.services;

import dev.apizana.tictactoe.domain.dtos.UserDto;
import dev.apizana.tictactoe.models.User;
import dev.apizana.tictactoe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public User update(User user) {
        Optional<User> userFound = userRepository.findById(user.getId());
        if (userFound.isEmpty())
            return null;
        User updatedUser = new User();

        return userRepository.save(user);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("User not found with username: " + username);
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
    }

    public dev.apizana.tictactoe.models.User save(UserDto user) {
        dev.apizana.tictactoe.models.User newUser = new dev.apizana.tictactoe.models.User();
        String userPassword = user.getPassword();
        String userEmail = user.getUsername();
        newUser.setUsername(userEmail);
        newUser.setPassword(bcryptEncoder.encode(userPassword));
        newUser.setEmail("testserver@email.com");
        return userRepository.save(newUser);
    }
}
