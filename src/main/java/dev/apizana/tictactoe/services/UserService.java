package dev.apizana.tictactoe.services;

import dev.apizana.tictactoe.models.User;
import dev.apizana.tictactoe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.*;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    /*
    @Autowired
    private Validator validator;
*/
    //@Validated(OnCreate.class)
    public User create(User user) {
        /*ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }*/
        return userRepository.save(user);
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

}
