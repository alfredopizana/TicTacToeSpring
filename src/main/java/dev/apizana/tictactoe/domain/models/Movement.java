package dev.apizana.tictactoe.domain.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
/*
@Entity(name="movement")
public class Movement {
    
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(targetEntity = Game.class)
    @JoinColumn(name = "gameId", foreignKey = @ForeignKey(name = "id"))
    private Long gameId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('cross', 'circle')")
    private MovementSymbol symbol;
    
    @NotNull
    @Size(min = 1, max = 9)
    @Column
    private Integer position;

    @NotNull
    @Size(min = 1, max = 9)
    @Column
    private Integer movementNumber;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant modifiedDate;

    //setters and getters

    public Long getId() {
        return this.id;
    }

    public Long getGameId() {
        return this.gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public MovementSymbol getSymbol() {
        return this.symbol;
    }

    public void setSymbol(MovementSymbol symbol) {
        this.symbol = symbol;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getMovementNumber() {
        return this.movementNumber;
    }

    public void setMovementNumber(Integer movementNumber) {
        this.movementNumber = movementNumber;
    }


}*/