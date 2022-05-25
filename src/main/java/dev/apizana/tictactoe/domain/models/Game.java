package dev.apizana.tictactoe.domain.models;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
/*
@Entity(name="game")
@Table(name="game")
public class Game {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "gameId", cascade = CascadeType.ALL)
    private List<Movement> history;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('random', 'ai', 'versus')")
    private GameMode gameMode = GameMode.random;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('notstarted', 'inprogress', 'completed')")
    private GameStatus gameStatus = GameStatus.notstarted;

    @Column
    private String winner;

    @ManyToOne(targetEntity = User.class)
    private Long creator;

    @CreatedBy
    private Long createdBy;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant modifiedDate;

    //setters and getters

    public Long getId() {
        return this.id;
    }
    public List<Movement> getAllMovements(){
        return this.history;
    }

    public void setHistory(List<Movement> history) {
        this.history = history;
    }


    public GameMode getGameMode() {
        return this.gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getWinner() {
        return this.winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
*/