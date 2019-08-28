package it.polito.dp2.RNS.sol3.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.net.URI;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * A connection between two places in the RNS system
 **/
@ApiModel(description = "A connection between two places in the RNS system")
public class Connection   {
  
  private @Valid URI self;
  private @Valid String id;
  private @Valid URI from;
  private @Valid URI to;

  /**
   * An RNS connection URI (so it can have its proper example)
   **/
  public Connection self(URI self) {
    this.self = self;
    return this;
  }

  
  @ApiModelProperty(example = "http://localhost:8080/RnsSystem/rest/connections/...", value = "An RNS connection URI (so it can have its proper example)")
  @JsonProperty("self")
  public URI getSelf() {
    return self;
  }
  public void setSelf(URI self) {
    this.self = self;
  }

  /**
   * How a connection between two places is identified
   **/
  public Connection id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(example = "connection123", value = "How a connection between two places is identified")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   * An RNS generic place URI (so it can have its proper example)
   **/
  public Connection from(URI from) {
    this.from = from;
    return this;
  }

  
  @ApiModelProperty(example = "http://localhost:8080/RnsSystem/rest/places/place123", required = true, value = "An RNS generic place URI (so it can have its proper example)")
  @JsonProperty("from")
  @NotNull
  public URI getFrom() {
    return from;
  }
  public void setFrom(URI from) {
    this.from = from;
  }

  /**
   * An RNS generic place URI (so it can have its proper example)
   **/
  public Connection to(URI to) {
    this.to = to;
    return this;
  }

  
  @ApiModelProperty(example = "http://localhost:8080/RnsSystem/rest/places/place123", required = true, value = "An RNS generic place URI (so it can have its proper example)")
  @JsonProperty("to")
  @NotNull
  public URI getTo() {
    return to;
  }
  public void setTo(URI to) {
    this.to = to;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Connection connection = (Connection) o;
    return Objects.equals(this.self, connection.self) &&
        Objects.equals(this.id, connection.id) &&
        Objects.equals(this.from, connection.from) &&
        Objects.equals(this.to, connection.to);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, id, from, to);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Connection {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
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

