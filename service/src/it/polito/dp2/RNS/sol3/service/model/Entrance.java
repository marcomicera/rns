package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.polito.dp2.RNS.sol3.service.model.EntranceGate;
import it.polito.dp2.RNS.sol3.service.model.EntranceVehicle;
import java.net.URI;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * A vehicle entrance request in the RNS system
 **/
@ApiModel(description = "A vehicle entrance request in the RNS system")
public class Entrance   {
  
  private @Valid EntranceGate gate;
  private @Valid EntranceVehicle vehicle;
  private @Valid URI destination;

  /**
   **/
  public Entrance gate(EntranceGate gate) {
    this.gate = gate;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("gate")
  public EntranceGate getGate() {
    return gate;
  }
  public void setGate(EntranceGate gate) {
    this.gate = gate;
  }

  /**
   **/
  public Entrance vehicle(EntranceVehicle vehicle) {
    this.vehicle = vehicle;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("vehicle")
  public EntranceVehicle getVehicle() {
    return vehicle;
  }
  public void setVehicle(EntranceVehicle vehicle) {
    this.vehicle = vehicle;
  }

  /**
   * An RNS generic place URI (so it can have its proper example)
   **/
  public Entrance destination(URI destination) {
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
    Entrance entrance = (Entrance) o;
    return Objects.equals(this.gate, entrance.gate) &&
        Objects.equals(this.vehicle, entrance.vehicle) &&
        Objects.equals(this.destination, entrance.destination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gate, vehicle, destination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Entrance {\n");
    
    sb.append("    gate: ").append(toIndentedString(gate)).append("\n");
    sb.append("    vehicle: ").append(toIndentedString(vehicle)).append("\n");
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

