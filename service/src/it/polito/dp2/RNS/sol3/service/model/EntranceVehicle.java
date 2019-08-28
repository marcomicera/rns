package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.polito.dp2.RNS.sol3.service.model.VehicleType;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



public class EntranceVehicle   {
  
  private @Valid String vehicleId;
  private @Valid VehicleType vehicleType;

  /**
   * A vehicle plate ID (so it can have its proper example)
   **/
  public EntranceVehicle vehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
    return this;
  }

  
  @ApiModelProperty(example = "vehicle123", value = "A vehicle plate ID (so it can have its proper example)")
  @JsonProperty("vehicleId")
  public String getVehicleId() {
    return vehicleId;
  }
  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  /**
   **/
  public EntranceVehicle vehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("vehicleType")
  public VehicleType getVehicleType() {
    return vehicleType;
  }
  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EntranceVehicle entranceVehicle = (EntranceVehicle) o;
    return Objects.equals(this.vehicleId, entranceVehicle.vehicleId) &&
        Objects.equals(this.vehicleType, entranceVehicle.vehicleType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vehicleId, vehicleType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntranceVehicle {\n");
    
    sb.append("    vehicleId: ").append(toIndentedString(vehicleId)).append("\n");
    sb.append("    vehicleType: ").append(toIndentedString(vehicleType)).append("\n");
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

