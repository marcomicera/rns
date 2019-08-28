package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * A freshly-insterted vehicle in the RNS system
 **/
@ApiModel(description = "A freshly-insterted vehicle in the RNS system")
public class NewVehicle   {
  
  private @Valid URI self;
  private @Valid String id;
  private @Valid List<URI> suggestedPath = new ArrayList<URI>();

  /**
   * An RNS vehicle URI (so it can have its proper example)
   **/
  public NewVehicle self(URI self) {
    this.self = self;
    return this;
  }

  
  @ApiModelProperty(example = "http://localhost:8080/RnsSystem/rest/vehicles/vehicle123", value = "An RNS vehicle URI (so it can have its proper example)")
  @JsonProperty("self")
  public URI getSelf() {
    return self;
  }
  public void setSelf(URI self) {
    this.self = self;
  }

  /**
   * A vehicle plate ID (so it can have its proper example)
   **/
  public NewVehicle id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(example = "vehicle123", value = "A vehicle plate ID (so it can have its proper example)")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Path of places suggested to the user upon vehicle entrance and movement
   **/
  public NewVehicle suggestedPath(List<URI> suggestedPath) {
    this.suggestedPath = suggestedPath;
    return this;
  }

  
  @ApiModelProperty(example = "[\"http://localhost:8080/RnsSystem/rest/places/suggestedPlace1\",\"http://localhost:8080/RnsSystem/rest/places/suggestedPlace2\",\"http://localhost:8080/RnsSystem/rest/places/suggestedPlace3\"]", value = "Path of places suggested to the user upon vehicle entrance and movement")
  @JsonProperty("suggestedPath")
 @Size(min=1)  public List<URI> getSuggestedPath() {
    return suggestedPath;
  }
  public void setSuggestedPath(List<URI> suggestedPath) {
    this.suggestedPath = suggestedPath;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewVehicle newVehicle = (NewVehicle) o;
    return Objects.equals(this.self, newVehicle.self) &&
        Objects.equals(this.id, newVehicle.id) &&
        Objects.equals(this.suggestedPath, newVehicle.suggestedPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, id, suggestedPath);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewVehicle {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    suggestedPath: ").append(toIndentedString(suggestedPath)).append("\n");
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

