package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.polito.dp2.RNS.sol3.service.model.BasePlace;
import it.polito.dp2.RNS.sol3.service.model.ParkingAreaAllOf;
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
 * A parking area in the RNS system
 **/
@ApiModel(description = "A parking area in the RNS system")
public class ParkingArea   {
  
  private @Valid URI self;
  private @Valid String id;
  private @Valid Integer capacity;
  private @Valid List<URI> nextPlaces = new ArrayList<URI>();
  private @Valid List<String> services = new ArrayList<String>();

  /**
   * An RNS generic place URI (so it can have its proper example)
   **/
  public ParkingArea self(URI self) {
    this.self = self;
    return this;
  }

  
  @ApiModelProperty(example = "http://localhost:8080/RnsSystem/rest/places/place123", required = true, value = "An RNS generic place URI (so it can have its proper example)")
  @JsonProperty("self")
  @NotNull
  public URI getSelf() {
    return self;
  }
  public void setSelf(URI self) {
    this.self = self;
  }

  /**
   * The ID of a place in the RNS system
   **/
  public ParkingArea id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(example = "place123", required = true, value = "The ID of a place in the RNS system")
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   * The capacity of a place in terms of supported vehicles
   * minimum: 0
   **/
  public ParkingArea capacity(Integer capacity) {
    this.capacity = capacity;
    return this;
  }

  
  @ApiModelProperty(example = "100", required = true, value = "The capacity of a place in terms of supported vehicles")
  @JsonProperty("capacity")
  @NotNull
 @Min(0)  public Integer getCapacity() {
    return capacity;
  }
  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  /**
   * A list of reachable generic place URIs (so it can have its proper example)
   **/
  public ParkingArea nextPlaces(List<URI> nextPlaces) {
    this.nextPlaces = nextPlaces;
    return this;
  }

  
  @ApiModelProperty(example = "[\"http://localhost:8080/RnsSystem/rest/places/place1\",\"http://localhost:8080/RnsSystem/rest/places/place2\",\"http://localhost:8080/RnsSystem/rest/places/place3\"]", value = "A list of reachable generic place URIs (so it can have its proper example)")
  @JsonProperty("nextPlaces")
 @Size(min=0)  public List<URI> getNextPlaces() {
    return nextPlaces;
  }
  public void setNextPlaces(List<URI> nextPlaces) {
    this.nextPlaces = nextPlaces;
  }

  /**
   * List of provided services of a parking area
   **/
  public ParkingArea services(List<String> services) {
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
    ParkingArea parkingArea = (ParkingArea) o;
    return Objects.equals(this.self, parkingArea.self) &&
        Objects.equals(this.id, parkingArea.id) &&
        Objects.equals(this.capacity, parkingArea.capacity) &&
        Objects.equals(this.nextPlaces, parkingArea.nextPlaces) &&
        Objects.equals(this.services, parkingArea.services);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, id, capacity, nextPlaces, services);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParkingArea {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    capacity: ").append(toIndentedString(capacity)).append("\n");
    sb.append("    nextPlaces: ").append(toIndentedString(nextPlaces)).append("\n");
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

