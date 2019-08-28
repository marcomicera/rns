package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The state a of vehicle in the RNS system
 */
public enum VehicleStatus {
  
  PARKED("PARKED"),
  
  IN_TRANSIT("IN_TRANSIT");

  private String value;

  VehicleStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static VehicleStatus fromValue(String value) {
    for (VehicleStatus b : VehicleStatus.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}


