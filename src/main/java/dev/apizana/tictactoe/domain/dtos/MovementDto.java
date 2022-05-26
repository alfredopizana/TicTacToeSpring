package dev.apizana.tictactoe.domain.dtos;

import dev.apizana.tictactoe.domain.models.Game;
import dev.apizana.tictactoe.domain.models.MovementSymbol;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovementDto {

    private MovementSymbol symbol;

    private Integer position;

    private Integer movementNumber;

}
