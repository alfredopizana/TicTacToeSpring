package dev.apizana.tictactoe.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthRequest implements Serializable {

    @NotNull(message = "Email is required")
    private String username;

    @NotNull(message = "password is required")
    private String password;


}
