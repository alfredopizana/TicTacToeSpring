package dev.apizana.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition
//@EntityScan("com.apizana.tictactoe.models")
//@EnableJpaRepositories("com.apizana.tictactoe.repositories.**.*")
public class TictactoeApplication {

	public static void main(String[] args) {

		SpringApplication.run(TictactoeApplication.class, args);
	}
}
