package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



public class ParkingAreaAllOf   {
  
  private @Valid List<String> services = new ArrayList<String>();

  /**
   * List of provided services of a parking area
   **/
  public ParkingAreaAllOf services(List<String> services) {
    this.services = services;
    return this;
  }

  
  @ApiModelProperty(example = "[\"Restroom\",\"Bistro\",\"Smoking area\"]", value = "List of provided services of a parking area")
  @JsonProperty("services")
 @Size(min=0)  public List<String> getServices() {
    return services;
  }
  public void setServices(List<String> services) {
    this.services = services;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParkingAreaAllOf parkingAreaAllOf = (ParkingAreaAllOf) o;
    return Objects.equals(this.services, parkingAreaAllOf.services);
  }

  @Override
  public int hashCode() {
    return Objects.hash(services);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParkingAreaAllOf {\n");
    
    sb.append("    services: ").append(toIndentedString(services)).append("\n");
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

