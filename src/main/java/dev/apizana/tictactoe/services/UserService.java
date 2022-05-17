package dev.apizana.tictactoe.services;

import dev.apizana.tictactoe.models.User;
import dev.apizana.tictactoe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    public User create(User user) {

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
