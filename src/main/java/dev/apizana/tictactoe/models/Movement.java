package dev.apizana.tictactoe.models;

import javax.persistence.*;

@Entity
public class Movement {
    @javax.persistence.Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Integer Id;
    @ManyToOne
    @JoinColumn(name = "gameId", foreignKey = @ForeignKey(name = "id"), referencedColumnName = "id")
    private Integer gameId;
    private String playedBy;
    private Integer position;

    //setters and getters
    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
}
