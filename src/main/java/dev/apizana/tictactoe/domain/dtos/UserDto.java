package dev.apizana.tictactoe.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    @Email(message = "Invalid email")
    private String email;

    private String password;

    private String username;

}
