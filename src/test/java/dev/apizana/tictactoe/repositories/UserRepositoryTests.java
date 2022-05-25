package dev.apizana.tictactoe.repositories;


import dev.apizana.tictactoe.domain.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;  // main one
import static org.assertj.core.api.Assertions.atIndex; // for List assertions
import static org.assertj.core.api.Assertions.entry;  // for Map assertions
import static org.assertj.core.api.Assertions.tuple; // when extracting several properties at once
import static org.assertj.core.api.Assertions.fail; // use when writing exception tests
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown; // idem
import static org.assertj.core.api.Assertions.filter; // for Iterable/Array assertions
import static org.assertj.core.api.Assertions.offset; // for floating number assertions
import static org.assertj.core.api.Assertions.anyOf; // use with Condition
import static org.assertj.core.api.Assertions.contentOf; // use with File assertions
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class UserRepositoryTests {

    @Autowired
    private UserRepository underTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    /**
     *  Test FindAllByActiveTrue method that get only active users.
     *  {@link dev.apizana.tictactoe.repositories.UserRepository}
     */
    @Test
    void canFindAllUsersByActiveTrue() {
        //given
        User user = new User();
        user.setEmail("email@test.com");
        user.setUsername("userTest");
        user.setPassword("as12qweqwe$%!1.");
        underTest.save(user);

        User user2 = new User();
        user2.setEmail("email2@test.com");
        user2.setUsername("userTest2");
        user2.setPassword("as12qweqwe$%!1.2");
        user2.setActive(false);
        underTest.save(user2);

        //when
        List<User> usersFound= underTest.findAllByActiveTrue();

        //then

        assertThat(usersFound)
                .isNotNull();

        assertThat(usersFound.size())
                .isEqualTo(1);

        assertThat(usersFound)
                .allSatisfy(userFound ->{
                    assertThat(userFound.getActive())
                        .isTrue();
        });
    }

    /**
     *  Test FindAllByActiveTrue method that empty if there are not active users.
     *  {@link dev.apizana.tictactoe.repositories.UserRepository}
     */
    @Test
    void willReturnEmptyListWhenFindAllUserByActiveTrueCountIsZero() {
        //gi
        //when
        List<User> usersFound= underTest.findAllByActiveTrue();
        assertThat(usersFound)
                .isNotNull();

        assertThat(usersFound.size())
                .isEqualTo(0);
    }



    /**
     *  Test canFindUserByEmail method from method findUserByEmailOrUsername.
     *  {@link dev.apizana.tictactoe.repositories.UserRepository}
     */
    @Test
    void canFindUserByEmail() {
        //given
        User user = new User();
        user.setEmail("email@test.com");
        user.setUsername("userTest");
        user.setPassword("as12qweqwe$%!1.");
        underTest.save(user);

        //when
        Optional<User> userFound = underTest.findByEmailOrUsername(user.getEmail(),new String());

        //then
        assertThat(userFound)
                .isPresent();

        assertThat(userFound.get().getEmail())
                .isEqualTo(user.getEmail());

    }

    /**
     *  Test NoResult response from method findUserByEmail when email is not found.
     *  {@link dev.apizana.tictactoe.repositories.UserRepository}
     */
    @Test
    void shouldReturnNoResultFromOptionalWhenFindUserByEmailIsNotFound() {
        //given
        User user = new User();
        user.setEmail("email@test.com");
        user.setUsername("userTest");
        user.setPassword("as12qweqwe$%!1.");
        underTest.save(user);

        //given
        User user2 = new User();
        user2.setEmail("email2@test.com");
        user2.setUsername("userTest2");
        user2.setPassword("as12qweqwe$%!1.2");
        underTest.save(user2);

        //when
        Optional<User> userFound = underTest.findByEmailOrUsername("notarecord@email.com",new String());

        //then
        assertThat(userFound)
                .isEmpty();
        assertThat(userFound).isNotNull();
    }

    /**
     *  Test canFindUserByUsername method from method findUserByEmailOrUsername.
     *  {@link dev.apizana.tictactoe.repositories.UserRepository}
     */
    @Test
    void canFindUserByUsername() {
        //given
        User user = new User();
        user.setEmail("email@test.com");
        user.setUsername("userTest");
        user.setPassword("as12qweqwe$%!1.");
        underTest.save(user);

        //when
        Optional<User> userFound = underTest.findByEmailOrUsername(new String(),user.getUsername());

        //then

        assertThat(userFound)
                .isPresent();

        assertThat(userFound.get().getUsername())
                .isEqualTo(user.getUsername());

    }

    /**
     *  Test NoResult response from method findUserByUsername when username is not found.
     *  {@link dev.apizana.tictactoe.repositories.UserRepository}
     */
    @Test
    void shouldReturnNoResultFromOptionalWhenFindUserByUsernameIsNotFound() {
        //given
        User user = new User();
        user.setEmail("email@test.com");
        user.setUsername("userTest");
        user.setPassword("as12qweqwe$%!1.");
        underTest.save(user);

        //given
        User user2 = new User();
        user2.setEmail("email2@test.com");
        user2.setUsername("userTest2");
        user2.setPassword("as12qweqwe$%!1.2");
        underTest.save(user2);

        //when
        Optional<User> userFound = underTest.findByEmailOrUsername(new String(),"userTest3");

        //then
        assertThat(userFound)
                .isEmpty();
        assertThat(userFound).isNotNull();
    }

    /**
     *  Test findByUsernameAndActiveTrue method when a valid and active true user is provided.
     *  {@link dev.apizana.tictactoe.repositories.UserRepository}
     */
    @Test
    void canFindByUsernameAndActiveTrue() {
        //given
        User user = new User();
        user.setEmail("email@test.com");
        user.setUsername("userTest");
        user.setPassword("as12qweqwe$%!1.");
        underTest.save(user);

        //given
        User user2 = new User();
        user2.setEmail("email2@test.com");
        user2.setUsername("userTest2");
        user2.setPassword("as12qweqwe$%!1.2");
        user2.setActive(false);
        underTest.save(user2);

        //when
        Optional<User> userFound = underTest.findByUsernameAndActiveTrue(user.getUsername());

        //then
        assertThat(userFound)
                .isPresent();
        assertThat(userFound.get().getEmail())
                .isEqualTo(user.getEmail());
        assertThat(userFound.get().getActive()).isTrue();
    }

    /**
     *  Test findByUsernameAndActiveTrue method when a valid and active true user is provided.
     *  {@link dev.apizana.tictactoe.repositories.UserRepository}
     */
    @Test
    void shouldReturnNoResultWhenUserActiveIsFalseFromFindByUsernameAndActiveTrue() {
        //given
        User user = new User();
        user.setEmail("email@test.com");
        user.setUsername("userTest");
        user.setPassword("as12qweqwe$%!1.");
        user.setActive(false);
        underTest.save(user);

        //given
        User user2 = new User();
        user2.setEmail("email2@test.com");
        user2.setUsername("userTest2");
        user2.setPassword("as12qweqwe$%!1.2");
        user2.setActive(false);
        underTest.save(user2);

        //when
        Optional<User> userFound = underTest.findByUsernameAndActiveTrue(user.getEmail());

        //then
        assertThat(userFound)
                .isEmpty();
        assertThat(userFound)
                .isNotNull();

    }
}