package dev.apizana.tictactoe.domain.dtos;

import dev.apizana.tictactoe.domain.models.GameMode;
import dev.apizana.tictactoe.domain.models.GameStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameDto {

    private String creator;

    private GameMode gameMode;

    private GameStatus gameStatus;

    private String winner;
}
