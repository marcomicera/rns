package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * All possible types of gates in the RNS system
 */
public enum GateType {
  
  IN("IN"),
  
  OUT("OUT"),
  
  INOUT("INOUT");

  private String value;

  GateType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static GateType fromValue(String value) {
    for (GateType b : GateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}


