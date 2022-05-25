package dev.apizana.tictactoe.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class AuthResponse implements Serializable {
    private final String jwttoken;
}
