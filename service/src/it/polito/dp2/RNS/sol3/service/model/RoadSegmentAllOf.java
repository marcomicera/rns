package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



public class RoadSegmentAllOf   {
  
  private @Valid String name;
  private @Valid String road;

  /**
   * Name of a road segment
   **/
  public RoadSegmentAllOf name(String name) {
    this.name = name;
    return this;
  }

  
  @ApiModelProperty(example = "RS123", required = true, value = "Name of a road segment")
  @JsonProperty("name")
  @NotNull
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   * A road name to which a road segment can belong to
   **/
  public RoadSegmentAllOf road(String road) {
    this.road = road;
    return this;
  }

  
  @ApiModelProperty(example = "White Avenue", required = true, value = "A road name to which a road segment can belong to")
  @JsonProperty("road")
  @NotNull
  public String getRoad() {
    return road;
  }
  public void setRoad(String road) {
    this.road = road;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoadSegmentAllOf roadSegmentAllOf = (RoadSegmentAllOf) o;
    return Objects.equals(this.name, roadSegmentAllOf.name) &&
        Objects.equals(this.road, roadSegmentAllOf.road);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, road);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoadSegmentAllOf {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    road: ").append(toIndentedString(road)).append("\n");
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

