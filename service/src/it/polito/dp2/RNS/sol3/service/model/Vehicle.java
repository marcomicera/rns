package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.polito.dp2.RNS.sol3.service.model.VehicleStatus;
import it.polito.dp2.RNS.sol3.service.model.VehicleType;
import java.net.URI;
import java.util.Date;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * A single vehicle in the RNS system
 **/
@ApiModel(description = "A single vehicle in the RNS system")
public class Vehicle   {
  
  private @Valid URI self;
  private @Valid String id;
  private @Valid Date entryTime;
  private @Valid VehicleType type;
  private @Valid VehicleStatus state;
  private @Valid URI origin;
  private @Valid URI position;
  private @Valid URI destination;

  /**
   * An RNS vehicle URI (so it can have its proper example)
   **/
  public Vehicle self(URI self) {
    this.self = self;
    return this;
  }

  
  @ApiModelProperty(example = "http://localhost:8080/RnsSystem/rest/vehicles/vehicle123", required = true, value = "An RNS vehicle URI (so it can have its proper example)")
  @JsonProperty("self")
  @NotNull
  public URI getSelf() {
    return self;
  }
  public void setSelf(URI self) {
    this.self = self;
  }

  /**
   * A vehicle plate ID (so it can have its proper example)
   **/
  public Vehicle id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(example = "vehicle123", required = true, value = "A vehicle plate ID (so it can have its proper example)")
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   * The entry time in which a vehicle has entered the RNS sytem
   **/
  public Vehicle entryTime(Date entryTime) {
    this.entryTime = entryTime;
    return this;
  }

  
  @ApiModelProperty(example = "2017-07-21T17:32:28Z", required = true, value = "The entry time in which a vehicle has entered the RNS sytem")
  @JsonProperty("entryTime")
  @NotNull
  public Date getEntryTime() {
    return entryTime;
  }
  public void setEntryTime(Date entryTime) {
    this.entryTime = entryTime;
  }

  /**
   **/
  public Vehicle type(VehicleType type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("type")
  @NotNull
  public VehicleType getType() {
    return type;
  }
  public void setType(VehicleType type) {
    this.type = type;
  }

  /**
   **/
  public Vehicle state(VehicleStatus state) {
    this.state = state;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("state")
  @NotNull
  public VehicleStatus getState() {
    return state;
  }
  public void setState(VehicleStatus state) {
    this.state = state;
  }

  /**
   * An RNS generic place URI (so it can have its proper example)
   **/
  public Vehicle origin(URI origin) {
    this.origin = origin;
    return this;
  }

  
  @ApiModelProperty(example = "http://localhost:8080/RnsSystem/rest/places/place123", required = true, value = "An RNS generic place URI (so it can have its proper example)")
  @JsonProperty("origin")
  @NotNull
  public URI getOrigin() {
    return origin;
  }
  public void setOrigin(URI origin) {
    this.origin = origin;
  }

  /**
   * An RNS generic place URI (so it can have its proper example)
   **/
  public Vehicle position(URI position) {
    this.position = position;
    return this;
  }

  
  @ApiModelProperty(example = "http://localhost:8080/RnsSystem/rest/places/place123", required = true, value = "An RNS generic place URI (so it can have its proper example)")
  @JsonProperty("position")
  @NotNull
  public URI getPosition() {
    return position;
  }
  public void setPosition(URI position) {
    this.position = position;
  }

  /**
   * An RNS generic place URI (so it can have its proper example)
   **/
  public Vehicle destination(URI destination) {
    this.destination = destination;
    return this;
  }

  
  @ApiModelProperty(example = "http://localhost:8080/RnsSystem/rest/places/place123", required = true, value = "An RNS generic place URI (so it can have its proper example)")
  @JsonProperty("destination")
  @NotNull
  public URI getDestination() {
    return destination;
  }
  public void setDestination(URI destination) {
    this.destination = destination;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vehicle vehicle = (Vehicle) o;
    return Objects.equals(this.self, vehicle.self) &&
        Objects.equals(this.id, vehicle.id) &&
        Objects.equals(this.entryTime, vehicle.entryTime) &&
        Objects.equals(this.type, vehicle.type) &&
        Objects.equals(this.state, vehicle.state) &&
        Objects.equals(this.origin, vehicle.origin) &&
        Objects.equals(this.position, vehicle.position) &&
        Objects.equals(this.destination, vehicle.destination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, id, entryTime, type, state, origin, position, destination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Vehicle {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    entryTime: ").append(toIndentedString(entryTime)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    destination: ").append(toIndentedString(destination)).append("\n");
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

