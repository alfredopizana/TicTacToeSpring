package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Movement;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Game
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-13T04:30:20.933Z")


public class Game   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  /**
   * Type of game selected
   */
  public enum GameModeEnum {
    RANDOM("random"),
    
    AI("ai"),
    
    VERSUS("versus");

    private String value;

    GameModeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static GameModeEnum fromValue(String text) {
      for (GameModeEnum b : GameModeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("gameMode")
  private GameModeEnum gameMode = GameModeEnum.RANDOM;

  /**
   * current status of the game
   */
  public enum StatusEnum {
    NOTSTARTED("notstarted"),
    
    INPROGRESS("inprogress"),
    
    COMPLETED("completed");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = StatusEnum.NOTSTARTED;

  @JsonProperty("winner")
  private Object winner = null;

  @JsonProperty("nextMovement")
  private Object nextMovement = null;

  @JsonProperty("history")
  @Valid
  private List<Movement> history = null;

  @JsonProperty("createdBy")
  private Integer createdBy = null;

  @JsonProperty("active")
  private Boolean active = true;

  @JsonProperty("created")
  private OffsetDateTime created = null;

  @JsonProperty("modified")
  private OffsetDateTime modified = null;

  public Game id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Game name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Game gameMode(GameModeEnum gameMode) {
    this.gameMode = gameMode;
    return this;
  }

  /**
   * Type of game selected
   * @return gameMode
  **/
  @ApiModelProperty(value = "Type of game selected")


  public GameModeEnum getGameMode() {
    return gameMode;
  }

  public void setGameMode(GameModeEnum gameMode) {
    this.gameMode = gameMode;
  }

  public Game status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * current status of the game
   * @return status
  **/
  @ApiModelProperty(value = "current status of the game")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Game winner(Object winner) {
    this.winner = winner;
    return this;
  }

  /**
   * Get winner
   * @return winner
  **/
  @ApiModelProperty(value = "")


  public Object getWinner() {
    return winner;
  }

  public void setWinner(Object winner) {
    this.winner = winner;
  }

  public Game nextMovement(Object nextMovement) {
    this.nextMovement = nextMovement;
    return this;
  }

  /**
   * Get nextMovement
   * @return nextMovement
  **/
  @ApiModelProperty(value = "")


  public Object getNextMovement() {
    return nextMovement;
  }

  public void setNextMovement(Object nextMovement) {
    this.nextMovement = nextMovement;
  }

  public Game history(List<Movement> history) {
    this.history = history;
    return this;
  }

  public Game addHistoryItem(Movement historyItem) {
    if (this.history == null) {
      this.history = new ArrayList<Movement>();
    }
    this.history.add(historyItem);
    return this;
  }

  /**
   * Get history
   * @return history
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Movement> getHistory() {
    return history;
  }

  public void setHistory(List<Movement> history) {
    this.history = history;
  }

  public Game createdBy(Integer createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  /**
   * Get createdBy
   * @return createdBy
  **/
  @ApiModelProperty(value = "")


  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  public Game active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
  **/
  @ApiModelProperty(value = "")


  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Game created(OffsetDateTime created) {
    this.created = created;
    return this;
  }

  /**
   * Get created
   * @return created
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCreated() {
    return created;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public Game modified(OffsetDateTime modified) {
    this.modified = modified;
    return this;
  }

  /**
   * Get modified
   * @return modified
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getModified() {
    return modified;
  }

  public void setModified(OffsetDateTime modified) {
    this.modified = modified;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Game game = (Game) o;
    return Objects.equals(this.id, game.id) &&
        Objects.equals(this.name, game.name) &&
        Objects.equals(this.gameMode, game.gameMode) &&
        Objects.equals(this.status, game.status) &&
        Objects.equals(this.winner, game.winner) &&
        Objects.equals(this.nextMovement, game.nextMovement) &&
        Objects.equals(this.history, game.history) &&
        Objects.equals(this.createdBy, game.createdBy) &&
        Objects.equals(this.active, game.active) &&
        Objects.equals(this.created, game.created) &&
        Objects.equals(this.modified, game.modified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, gameMode, status, winner, nextMovement, history, createdBy, active, created, modified);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Game {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    gameMode: ").append(toIndentedString(gameMode)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    winner: ").append(toIndentedString(winner)).append("\n");
    sb.append("    nextMovement: ").append(toIndentedString(nextMovement)).append("\n");
    sb.append("    history: ").append(toIndentedString(history)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

