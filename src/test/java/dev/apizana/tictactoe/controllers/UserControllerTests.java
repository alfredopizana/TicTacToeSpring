package dev.apizana.tictactoe.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.apizana.tictactoe.config.TestSecurityConfig;
import dev.apizana.tictactoe.domain.dtos.UserDto;
import dev.apizana.tictactoe.domain.models.User;
import dev.apizana.tictactoe.security.TokenUtil;
import dev.apizana.tictactoe.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.ValidationException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void canCreateNewUserWhenUserDoesNotExists() throws Exception {

        //given
        UserDto userRequest = new UserDto();
        userRequest.setUsername("userTest");
        userRequest.setEmail("email@test.com");
        userRequest.setPassword("password123");

        User userCreated = new User();
        userCreated.setId(1234L);
        userCreated.setCreatedDate(Instant.now());
        userCreated.setUsername(userRequest.getUsername());
        userCreated.setEmail(userRequest.getEmail());
        //when
        when(userService.create(userRequest)).thenReturn(userCreated);
        RequestBuilder request = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(userRequest))
                .accept(MediaType.APPLICATION_JSON);
        //then
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(userCreated.getUsername()))
                .andExpect(jsonPath("$.createdDate").isString())
                .andExpect(jsonPath("$.createdDate").value(userCreated.getCreatedDate().toString()))
                .andExpect(jsonPath("$.id").isNumber())

                .andExpect(jsonPath("$.id").value(userCreated.getId()));

    }

    @Test
    void shouldReturnBadRequestOnCreateWhenUsernameIsEmpty() throws Exception {

        //given
        UserDto userRequest = new UserDto();
        userRequest.setEmail("email@test.com");
        userRequest.setPassword("password123");
        //when
        //User testUser = userService.create(userRequest);
        RequestBuilder request = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(userRequest))
                .accept(MediaType.APPLICATION_JSON);
        //then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest());

    }
    @Test
    @Disabled
    void shouldThrownOnCreateWhenInternalServerError() throws Exception {

        //given
        UserDto userRequest = new UserDto();
        userRequest.setUsername("userTest");
        userRequest.setEmail("email@test.com");
        userRequest.setPassword("password123");

        User userCreated = new User();
        userCreated.setId(1234L);
        userCreated.setCreatedDate(Instant.now());
        userCreated.setUsername(userRequest.getUsername());
        userCreated.setEmail(userRequest.getEmail());
        //when
        when(userService.create(userRequest)).thenThrow(new ValidationException("User already exist in database"));
        //User testUser = userService.create(userRequest);
        RequestBuilder request = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(userRequest))
                .accept(MediaType.APPLICATION_JSON);
        //then
        mockMvc.perform(request)
                .andExpect(status().is5xxServerError());

    }



    @Test
    void canGetAllWithValidUsers() throws Exception {

        //given
        User user = new User();
        user.setId(123L);
        user.setEmail("email@test.com");
        user.setUsername("userTest");
        user.setPassword("as12qweqwe$%!1.");

        User user2 = new User();
        user2.setId(124L);
        user2.setEmail("email2@test.com");
        user2.setUsername("userTest2");
        user2.setPassword("as12qweqwe$%!1.2");

        List<User> usersFound= new ArrayList<>();
        usersFound.add(user);
        usersFound.add(user2);

        //when
        when(userService.getAllUsers()).thenReturn(usersFound);
        RequestBuilder request = MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].username").value(usersFound.get(0).getUsername()))
                .andExpect(jsonPath("$[0].createdDate").isString())
                .andExpect(jsonPath("$[0].createdDate").value(usersFound.get(0).getCreatedDate().toString()))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].id").value(usersFound.get(0).getId()))

                .andExpect(jsonPath("$[1].username").value(usersFound.get(1).getUsername()))
                .andExpect(jsonPath("$[1].createdDate").isString())
                .andExpect(jsonPath("$[1].createdDate").value(usersFound.get(1).getCreatedDate().toString()))
                .andExpect(jsonPath("$[1].id").isNumber())
                .andExpect(jsonPath("$[1].id").value(usersFound.get(1).getId()));

    }

    @Test
    void shouldReturnEmptyListWhenNotActiveUsers() throws Exception {

        //given

        List<User> usersFound= new ArrayList<>();


        //when
        when(userService.getAllUsers()).thenReturn(usersFound);
        RequestBuilder request = MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(request)
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    @Disabled
    void shouldThrownWhenInternalServerError() throws Exception {

        //given

        List<User> usersFound= new ArrayList<>();


        //when
        when(userService.getAllUsers()).thenThrow(mock(Exception.class));
        RequestBuilder request = MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(request)
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void canGetByUsernameWhenUsernameIsValid() throws Exception {

        //given
        String username = "userTest";

        User userFound = new User();
        userFound.setId(1234L);
        userFound.setCreatedDate(Instant.now());
        userFound.setUsername(username);
        userFound.setEmail("email@test.com");
        userFound.setPassword("password123");
        //when
        when(userService.getByUsername(username)).thenReturn(userFound);
        RequestBuilder request = MockMvcRequestBuilders.get("/users/"+ username)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON);
        //then
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(userFound.getUsername()))
                .andExpect(jsonPath("$.createdDate").isString())
                .andExpect(jsonPath("$.createdDate").value(userFound.getCreatedDate().toString()))
                .andExpect(jsonPath("$.id").isNumber())

                .andExpect(jsonPath("$.id").value(userFound.getId()));

    }

    @Test
    void canUpdateUserWhenEmailIsNotDuplicated() throws Exception{
        //given
        String username = "userTest";
        UserDto userRequest = new UserDto();
        userRequest.setEmail("emailNew@test.com");
        userRequest.setPassword("123456");

        User userFound = new User();
        userFound.setId(1234L);
        userFound.setCreatedDate(Instant.now());
        userFound.setUsername(username);
        userFound.setEmail("email@test.com");
        userFound.setPassword("password123");

        User userUpdated = new User();
        userUpdated.setId(userFound.getId());
        userUpdated.setCreatedDate(userFound.getCreatedDate());
        userUpdated.setUsername(userFound.getUsername());
        userUpdated.setEmail(userRequest.getEmail());
        userUpdated.setPassword(userRequest.getPassword());

        Authentication authentication = Mockito.mock(Authentication.class);
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userFound);
        SecurityContextHolder.setContext(securityContext);

        //when

        when(userService.update(username,userRequest)).thenReturn(userUpdated);
        RequestBuilder request = MockMvcRequestBuilders.patch("/users/"+ username)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(userRequest))
                .accept(MediaType.APPLICATION_JSON);
        //then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(userRequest.getEmail()))
                .andExpect(jsonPath("$.username").value(username));

    }

    @Test
    void willThrownUpdateUserWhenAuthUsernameIsDifferent() throws Exception{
        //given
        String username = "userTest";
        UserDto userRequest = new UserDto();
        userRequest.setEmail("emailNew@test.com");
        userRequest.setPassword("123456");

        User userFound = new User();
        userFound.setId(1234L);
        userFound.setCreatedDate(Instant.now());
        userFound.setUsername("anotherAuthUser");
        userFound.setEmail("anotherauthuser@test.com");
        userFound.setPassword("password123");

        Authentication authentication = Mockito.mock(Authentication.class);
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userFound);
        SecurityContextHolder.setContext(securityContext);


        RequestBuilder request = MockMvcRequestBuilders.patch("/users/"+ username)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(userRequest))
                .accept(MediaType.APPLICATION_JSON);
        //then
        mockMvc.perform(request)
                .andExpect(status().isUnauthorized());

    }

    @Test
    void canDeleteUserWhenUsernameIsActive() throws Exception{
        //given
        String username = "userTest";
        RequestBuilder request = MockMvcRequestBuilders.delete("/users/"+ username)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(username))
                .accept(MediaType.APPLICATION_JSON);
        //when
        when(userService.deleteByUsername(username)).thenReturn(true);
        //then
        mockMvc.perform(request)
                .andExpect(status().isAccepted());
    }
}