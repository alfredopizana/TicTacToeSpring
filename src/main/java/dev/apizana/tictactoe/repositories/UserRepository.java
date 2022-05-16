package dev.apizana.tictactoe.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
/*
@Service
public class UserRepository<User, Long extends Serializable> extends SimpleJpaRepository<User, Long> implements IUserRepository<User, Long> {
    @Autowired
    private EntityManager entityManager;

    public UserRepository(Class<User> domainClass, EntityManager em) {
        super(domainClass, em);
        // This is the recommended method for accessing inherited class dependencies.
        this.entityManager = em;
    }
}
*/