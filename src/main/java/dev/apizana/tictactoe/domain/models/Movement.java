package dev.apizana.tictactoe.domain.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;

@Entity//(name="movement")
@Table(name = "movements")
/*@Getter
@Setter*/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Movement {
    
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(targetEntity = Game.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "game", foreignKey = @ForeignKey(name = "id"))
    private Game game;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('cross', 'circle')")
    private MovementSymbol symbol;
    
    @NotNull
    //@Size(min = 1, max = 9)
    @Column
    private Integer position;

    @NotNull
    //@Size(min = 1, max = 9)
    @Column
    private Integer movementNumber;
}
