package dev.apizana.tictactoe.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.apizana.tictactoe.config.TestSecurityConfig;
import dev.apizana.tictactoe.domain.dtos.UserDto;
import dev.apizana.tictactoe.domain.models.User;
import dev.apizana.tictactoe.security.TokenUtil;
import dev.apizana.tictactoe.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    void shouldCreateNewUserWhenUserDoesNotExists() throws Exception {

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
        User testUser = userService.create(userRequest);
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
    void shouldReturnNullOnCreateWhenUserExists() throws Exception {

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


/*
    @Test
    void getAll() {

        //List<User> userList = ArrayList()

        RequestBuilder request = MockMvcRequestBuilders.get("/users")

        MvcResult result = mockMvc.perform(request).andReturn();

        //given
        UserDto userRequest = new UserDto();
        userRequest.setUsername("userTest");
        userRequest.setEmail("email@test.com");
        userRequest.setPassword("password123");





    }

    @Test
    public void testUpdateOrder() throws Exception {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setRazorpayPaymentId("22445566");
        String json = mapper.writeValueAsString(paymentResponse);
        mockMvc.perform(put("/api/order").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.success").value("true")).andExpect(jsonPath("$.message").isNotEmpty());
    }

*/
    @Test
    void getByUsername() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}