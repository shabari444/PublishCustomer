package com.prokarma.publishCustomer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/** PublishCustomerResponse */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2021-06-09T12:49:17.298Z")
public class PublishCustomerResponse {

  @JsonProperty("status")
  private String status;

  @JsonProperty("message")
  private String message;



  public PublishCustomerResponse status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   *
   * @return status
   */
  @ApiModelProperty(value = "")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public PublishCustomerResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   *
   * @return message
   */
  @ApiModelProperty(value = "")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  
  
    @Override public boolean equals(java.lang.Object o) { if (this == o) { return true; } if (o ==
    null || getClass() != o.getClass()) { return false; } PublishCustomerResponse
    publishCustomerResponse = (PublishCustomerResponse) o; return Objects.equals(this.status,
    publishCustomerResponse.status) && Objects.equals(this.message,
    publishCustomerResponse.message); }
    
    @Override public int hashCode() { return Objects.hash(status, message); }
   
   

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PublishCustomerResponse {\n");

    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }



}
