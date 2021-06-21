package com.prokarma.publishCustomer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/** ErrorResponse */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2021-06-09T12:49:17.298Z")
public class ErrorResponse {

  @JsonProperty("status")
  private String status;

  @JsonProperty("message")
  private String message;

  @JsonProperty("errorType")
  private Object errorType;

  public ErrorResponse status(String status) {
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

  public ErrorResponse message(String message) {
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

  public ErrorResponse errorType(Object errorType) {
    this.errorType = errorType;
    return this;
  }

  /**
   * Get errorType
   *
   * @return errorType
   */
  @ApiModelProperty(value = "")
  public Object getErrorType() {
    return errorType;
  }

  public void setErrorType(String errorType) {
    this.errorType = errorType;
  }


}
