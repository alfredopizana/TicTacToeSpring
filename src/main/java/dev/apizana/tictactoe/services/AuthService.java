package dev.apizana.tictactoe.services;

import dev.apizana.tictactoe.domain.dtos.UserDto;
import dev.apizana.tictactoe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        dev.apizana.tictactoe.models.User user = userRepository.findByUsername(username);
        if ("tictactoe".equals(username)) {
            return new User("tictactoe", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        }
        else if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
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
