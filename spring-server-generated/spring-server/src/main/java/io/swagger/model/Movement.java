package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Movement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-13T04:30:20.933Z")


public class Movement   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("gameId")
  private Integer gameId = null;

  @JsonProperty("playedBy")
  private String playedBy = null;

  @JsonProperty("position")
  private Integer position = null;

  @JsonProperty("created")
  private OffsetDateTime created = null;

  public Movement id(Integer id) {
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

  public Movement gameId(Integer gameId) {
    this.gameId = gameId;
    return this;
  }

  /**
   * Get gameId
   * @return gameId
  **/
  @ApiModelProperty(value = "")


  public Integer getGameId() {
    return gameId;
  }

  public void setGameId(Integer gameId) {
    this.gameId = gameId;
  }

  public Movement playedBy(String playedBy) {
    this.playedBy = playedBy;
    return this;
  }

  /**
   * Current movement played by
   * @return playedBy
  **/
  @ApiModelProperty(value = "Current movement played by")


  public String getPlayedBy() {
    return playedBy;
  }

  public void setPlayedBy(String playedBy) {
    this.playedBy = playedBy;
  }

  public Movement position(Integer position) {
    this.position = position;
    return this;
  }

  /**
   * Get position
   * @return position
  **/
  @ApiModelProperty(value = "")


  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public Movement created(OffsetDateTime created) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Movement movement = (Movement) o;
    return Objects.equals(this.id, movement.id) &&
        Objects.equals(this.gameId, movement.gameId) &&
        Objects.equals(this.playedBy, movement.playedBy) &&
        Objects.equals(this.position, movement.position) &&
        Objects.equals(this.created, movement.created);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, gameId, playedBy, position, created);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Movement {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    gameId: ").append(toIndentedString(gameId)).append("\n");
    sb.append("    playedBy: ").append(toIndentedString(playedBy)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
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

