package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.polito.dp2.RNS.sol3.service.model.Vehicle;
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
 * A list of vehicles in the RNS system
 **/
@ApiModel(description = "A list of vehicles in the RNS system")
public class Vehicles   {
  
  private @Valid Integer totalPages = 1;
  private @Valid Integer page = 0;
  private @Valid URI next;
  private @Valid List<Vehicle> vehicles = new ArrayList<Vehicle>();

  /**
   * Indicates the total number of pages in a multi-paged response
   * minimum: 1
   **/
  public Vehicles totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  
  @ApiModelProperty(example = "5", value = "Indicates the total number of pages in a multi-paged response")
  @JsonProperty("totalPages")
 @Min(1)  public Integer getTotalPages() {
    return totalPages;
  }
  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  /**
   * Indicates the page number of a multi-paged response
   * minimum: 0
   **/
  public Vehicles page(Integer page) {
    this.page = page;
    return this;
  }

  
  @ApiModelProperty(example = "2", value = "Indicates the page number of a multi-paged response")
  @JsonProperty("page")
 @Min(0)  public Integer getPage() {
    return page;
  }
  public void setPage(Integer page) {
    this.page = page;
  }

  /**
   * The next page in a multi-paged vehicles list response (so it can have its proper example)
   **/
  public Vehicles next(URI next) {
    this.next = next;
    return this;
  }

  
  @ApiModelProperty(example = "http://localhost:8080/RnsSystem/rest/vehicles/...", value = "The next page in a multi-paged vehicles list response (so it can have its proper example)")
  @JsonProperty("next")
  public URI getNext() {
    return next;
  }
  public void setNext(URI next) {
    this.next = next;
  }

  /**
   **/
  public Vehicles vehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("vehicles")
  @NotNull
 @Size(min=0)  public List<Vehicle> getVehicles() {
    return vehicles;
  }
  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vehicles vehicles = (Vehicles) o;
    return Objects.equals(this.totalPages, vehicles.totalPages) &&
        Objects.equals(this.page, vehicles.page) &&
        Objects.equals(this.next, vehicles.next) &&
        Objects.equals(this.vehicles, vehicles.vehicles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalPages, page, next, vehicles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Vehicles {\n");
    
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    next: ").append(toIndentedString(next)).append("\n");
    sb.append("    vehicles: ").append(toIndentedString(vehicles)).append("\n");
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

