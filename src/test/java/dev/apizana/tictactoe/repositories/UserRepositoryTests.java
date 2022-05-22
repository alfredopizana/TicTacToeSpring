package dev.apizana.tictactoe.repositories;

import dev.apizana.tictactoe.domain.models.User;

import org.assertj.core.api.AbstractAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
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

//import org.hamcrest.Matchers.*;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
class UserRepositoryTests {

    @Autowired
    private UserRepository underTest;

    /**
     *  canFindAllUsersByActiveTrue
     *  <p>
     *  Test FindAllByActive method that get only active users.
     *  {@link}
     *  {@link dev.apizana.tictactoe.repositories.UserRepository}
     *  {@linkplain dev.apizana.tictactoe.repositories.UserRepository}
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
        //AbstractAssert.setPrintAssertionsDescription(Boolean.parseBoolean("Validate the user is not null"));
        assertThat(usersFound)
                .as("User Found Not null")
                .isNull();
        assertEquals(1,usersFound.size());
        assertThat(usersFound.size())
                .as("Expected just one active user")
                .isEqualTo(1);
              //.withFailMessage("Got a %s , a different size than the excpetec [%s]",usersFound.size(),2);
        assertThat(usersFound)
                .as("Check userFounds to be Active")
                .allSatisfy(userFound ->{
                    assertThat(userFound.getActive())
                        .withFailMessage("The Object does not meet the requierement")
                        .isTrue();
        });
    }

    @Test
    void shouldReturnEmptyListWhenFindAllUserByActiveTrueCountIsZero() {
    }

    @Test
    void findByEmailOrUsername() {
    }

    @Test
    void findByUsername() {
    }


}