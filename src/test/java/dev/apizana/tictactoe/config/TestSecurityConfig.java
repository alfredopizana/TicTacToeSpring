package dev.apizana.tictactoe.config;


import dev.apizana.tictactoe.security.AuthEntryPoint;
import dev.apizana.tictactoe.security.RequestFilter;
import dev.apizana.tictactoe.services.UserService;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;


@TestConfiguration

public class TestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Disable CSRF
        httpSecurity.csrf().disable()
                // Permit all requests without authentication
                .authorizeRequests().anyRequest().permitAll();
    }
}
