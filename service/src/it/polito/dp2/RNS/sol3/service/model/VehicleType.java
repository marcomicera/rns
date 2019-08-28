package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The type of a vehicle in the RNS system
 */
public enum VehicleType {
  
  CAR("CAR"),
  
  TRUCK("TRUCK"),
  
  SHUTTLE("SHUTTLE"),
  
  CARAVAN("CARAVAN");

  private String value;

  VehicleType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static VehicleType fromValue(String value) {
    for (VehicleType b : VehicleType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}


