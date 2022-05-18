package dev.apizana.tictactoe.config;

import dev.apizana.tictactoe.repositories.UserRepository;
import dev.apizana.tictactoe.security.AuthEntryPoint;
import dev.apizana.tictactoe.security.RequestFilter;
import dev.apizana.tictactoe.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired

    private AuthService authService;

    @Autowired
    private RequestFilter requestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //Configure AuthenticationManager so that it knows from where to load
        // user from matching credentials
        // User BCryptPasswordEncoder
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }


*/
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**");
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        // Enable CORS and disable CSRF
        httpSecurity = httpSecurity.cors().and().csrf().disable();
        httpSecurity
                .cors().and().csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests()
                .antMatchers(

                        "/authenticate",
                        "/register").permitAll()
                // all other requests need to be authenticated
                .anyRequest().authenticated().and().httpBasic().and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
