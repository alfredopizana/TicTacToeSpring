package dev.apizana.tictactoe.services;

import dev.apizana.tictactoe.domain.dtos.UserDto;
import dev.apizana.tictactoe.domain.models.User;
import dev.apizana.tictactoe.repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.validation.ValidationException;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;  // main one
import static org.mockito.Mockito.*;

/**
 *
 */

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTests {

    @Mock
    private UserRepository userRepository;
    //@Mock
    @Mock
    private PasswordEncoder bcryptEncoder;
    @InjectMocks
    private UserService underTest;


    /**
     *  Test create method that map user, check if user already exist and encrypt password.
     *  {@link dev.apizana.tictactoe.services.UserService}
     */
    @Test
    void canCreateUserWhenUserDtoValidIsPassed() {
        //given
        UserDto user = new UserDto();
        user.setEmail("email@test.com");
        user.setUsername("userTest");
        user.setPassword("as12qweqwe$%!1.");

        User userResponse = new User();
        userResponse.setEmail("email@test.com");
        userResponse.setUsername("userTest");
        userResponse.setPassword(bcryptEncoder.encode("password123"));

        //when
        User userReponse = underTest.create(user);

        //then
        ArgumentCaptor<User> userArgumentCaptor =
                    ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(capturedUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(capturedUser.getPassword()).isEqualTo(bcryptEncoder.encode(user.getPassword()));
    }

    /**
     *  Test create method that user, check if user already exist and encrypt password.
     *  {@link dev.apizana.tictactoe.services.UserService}
     */
    @Test
    void willThrownWhenOnCreateWhenUserDtoAlreadyExist() {
        //given
        UserDto user = new UserDto();
        user.setEmail("email@test.com");
        user.setUsername("userTest");
        user.setPassword("as12qweqwe$%!1.");

        User userResponse = new User();
        userResponse.setEmail("email@test.com");
        userResponse.setUsername("userTest");
        userResponse.setPassword("as12qweqwe$%!1.");


        given(userRepository.findByEmailOrUsername(user.getEmail(),user.getUsername())).willReturn(Optional.of(userResponse));
        //when

        //then
        assertThatThrownBy(()->underTest.create(user))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("User already exist in database");

    }
    @Test
    //@Disabled
    void canGetAllUsers() {
        //when
        underTest.getAllUsers();
        //then
        verify(userRepository).findAllByActiveTrue();
    }

    @Test
    void canGetByUsername() {
        //given
        String username = "userTest";
        User userMockResponse = new User();
        userMockResponse.setEmail("email@test.com");
        userMockResponse.setUsername("userTest");
        userMockResponse.setPassword("as12qweqwe$%!1.");
        //when
        given(userRepository.findByUsernameAndActiveTrue(username)).willReturn(Optional.of(userMockResponse));
        //then
        User userServiceReponse = underTest.getByUsername(username);
        assertThat(userServiceReponse.getUsername()).isEqualTo(userMockResponse.getUsername());
        assertThat(userServiceReponse.getActive()).isEqualTo(userMockResponse.getActive());
    }
    @Test
    void willReturnNullWhenGetByUsernameIsNotFound() {
        //given
        String username = "userTest";
        //when
        given(userRepository.findByUsernameAndActiveTrue(username)).willReturn(Optional.empty());
        //then
        User userServiceReponse = underTest.getByUsername(username);
        assertThat(userServiceReponse).isNull();
    }
    @Test

     void canUpdateUserByUsernameWhenUsernameMatchWithToken() throws ValidationException{
        //given
        String username = "userTest";
        UserDto userRequest = new UserDto();
        userRequest.setEmail("updated@test.com");
        userRequest.setPassword("updated123");
        userRequest.setUsername("userTest");

        User loggedUserMock = new User();
        loggedUserMock.setUsername("userTest");
        loggedUserMock.setEmail("email@test.com");
        loggedUserMock.setPassword(bcryptEncoder.encode("password123"));

        User mappedUser = loggedUserMock;
        mappedUser.setEmail(userRequest.getEmail());
        mappedUser.setPassword(bcryptEncoder.encode(userRequest.getPassword()));



        given(userRepository.findByUsernameAndActiveTrue(username)).willReturn(Optional.of(loggedUserMock));
        given(userRepository.findByEmailOrUsername("updated@test.com","userTest")).willReturn(Optional.empty());
        given(userRepository.save(mappedUser)).willReturn(mappedUser);
        //when

        User userResponse = underTest.update(username,userRequest);


        //then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser.getEmail()).isEqualTo(mappedUser.getEmail());
        assertThat(capturedUser.getPassword()).isEqualTo(mappedUser.getPassword());

    }
    @Test
    void willThrownUpdateUserByUsernameWhenUsernameIsFound() throws ValidationException{
        //given
        String username = "userTest";
        UserDto userRequest = new UserDto();
        userRequest.setEmail("updated@test.com");
        userRequest.setPassword("updated123");
        userRequest.setUsername("userTest");

        User userFound = new User();
        userFound.setUsername("userTest");
        userFound.setEmail("email@test.com");
        userFound.setPassword(bcryptEncoder.encode("password123"));

        given(userRepository.findByEmailOrUsername("updated@test.com","userTest")).willReturn(Optional.of(userFound));
        //when

        //then

        assertThatThrownBy(()->underTest.update(username,userRequest))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("user already exist in database");


    }
    @Test
    void willThrownUpdateUserByUsernameWhenUsernameParamIsNotFound() throws ValidationException{
        //given
        String username = "userTest";
        UserDto userRequest = new UserDto();
        userRequest.setEmail("updated@test.com");
        userRequest.setPassword("updated123");
        userRequest.setUsername("userTest");

        User userFound = new User();
        userFound.setUsername("userTest");
        userFound.setEmail("email@test.com");
        userFound.setPassword(bcryptEncoder.encode("password123"));

        given(userRepository.findByEmailOrUsername(userRequest.getEmail(),userRequest.getUsername())).willReturn(Optional.empty());
        given(userRepository.findByUsernameAndActiveTrue("userTest")).willReturn(Optional.empty());
        //when

        //then

        assertThatThrownBy(()->underTest.update(username,userRequest))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("username not found");


    }
    @Test

    void canDeleteByUsernameWithActiveUser() {
        //given
        String username = "userTest";
        User userFound = new User();
        userFound.setEmail("email@test.com");
        userFound.setUsername("userTest");
        userFound.setPassword(bcryptEncoder.encode("password123"));
        userFound.setActive(true);


        Instant updatedDate = Instant.now();
        User userUpdated = new User();
        userUpdated.setActive(false);
        userUpdated.setEmail(userFound.getEmail());
        userUpdated.setUsername(username);
        userUpdated.setModifiedDate(updatedDate);


        given(userRepository.findByUsernameAndActiveTrue(username)).willReturn(Optional.of(userFound));

        //when
            Boolean deleteResponse = underTest.deleteByUsername(username);
        //then

        assertThat(deleteResponse).isTrue();
        /*verify(userRepository).save(userFound);*/
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser.getActive()).isFalse();

    }
    @Test
    void willReturnFalseDeleteByUsernameWhenUserIsNotFound() {
        //given
        String username = "userTest";

        given(userRepository.findByUsernameAndActiveTrue(username)).willReturn(Optional.empty());
        //when
        Boolean response = underTest.deleteByUsername(username);
        //then
        verify(userRepository, never()).save(new User());
        assertThat(response).isFalse();

    }
    @Test
    void canLoadUserByUsernameWithLoggedUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //given
        String username ="userTest";
        User userFound = new User();
        userFound.setUsername("userTest");
        userFound.setEmail("email@test.com");
        userFound.setPassword(encoder.encode("password123"));

        given(userRepository.findByUsernameAndActiveTrue(username))
                .willReturn(
                        Optional.of(userFound));
        //when
            UserDetails response = underTest.loadUserByUsername(username);

        //then
        assertThat(response).isNotNull().isInstanceOf(UserDetails.class);

        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        assertThat(response.getUsername()).isEqualTo(userFound.getUsername());

    }

    @Test
    void willThrownLoadUserByUsernameWhenUserNotFound() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //given
        String username ="userTest";
        User userFound = new User();
        userFound.setUsername("userTest");
        userFound.setEmail("email@test.com");
        userFound.setPassword(encoder.encode("password123"));

        given(userRepository.findByUsernameAndActiveTrue(username))
                .willReturn(
                        Optional.empty());
        //when
        //then
        assertThatThrownBy(()->underTest.loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("User not found with username: " + username);

    }

}