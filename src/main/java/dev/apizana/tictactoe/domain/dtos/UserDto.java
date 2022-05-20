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
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "username is required")
    private String username;

}
