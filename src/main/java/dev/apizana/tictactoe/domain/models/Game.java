package dev.apizana.tictactoe.domain.models;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Entity//(name="game")
@Table(name="games")
/*@Getter
@Setter*/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class Game {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    //@OneToMany(mappedBy = "gameId", cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//, mappedBy = "gameId")
    private List<Movement> history = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('random', 'ai', 'versus')")
    private GameMode gameMode = GameMode.random;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('notstarted', 'inprogress', 'completed')")
    private GameStatus gameStatus = GameStatus.notstarted;

    @Column
    private String winner;

    private String creator;

    //@ManyToOne(targetEntity = User.class)
    //private User creator;

    /*
    @ManyToOne(targetEntity = User.class)
    private Long creator;*/

    @CreatedBy
    private Long createdBy;


    @CreatedDate
    private Instant createdDate = Instant.now();


    @LastModifiedDate
    private Instant modifiedDate = Instant.now();
}
