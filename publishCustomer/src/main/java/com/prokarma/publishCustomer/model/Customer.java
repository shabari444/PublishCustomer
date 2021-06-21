package com.prokarma.publishCustomer.model;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/** Customer */
// @Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2021-06-09T12:49:17.298Z")
public class Customer implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  // @NotBlank(message = "Customer Number cannaot be balnk")
  @JsonProperty("customerNumber")
  private String customerNumber = null;

  // @NotBlank(message = "cannot be blank")
  @JsonProperty("firstName")
  private String firstName = null;

  // @NotBlank(message = "cannot be blank")
  @JsonProperty("lastName")
  private String lastName = null;
  // @NotBlank(message = "cannot be blank")
  @JsonProperty("birthdate")
  private String birthdate = null;
  // @NotBlank(message = "cannot be blank")
  @JsonProperty("country")
  private String country = null;
  // @NotBlank(message = "cannot be blank")
  @JsonProperty("countryCode")
  private String countryCode = null;
  // @NotBlank(message = "cannot be blank")
  @JsonProperty("mobileNumber")
  private String mobileNumber = null;

  // @NotBlank(message = "cannot be blank")
  @JsonProperty("email")
  private String email = null;

  /** Customer status in the store */
  public enum StatusEnum {
    RESTORED("Restored"),

    SUSPENDED("Suspended"),

    OPEN("Open"),

    CLOSED("Closed");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  //// @NotBlank(message = "cannot be blank")
  @JsonProperty("status")
  private StatusEnum status = null;

  //// @NotBlank(message = "cannot be blank")
  @JsonProperty("address")
  private Address address = null;

  public Customer customerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
    return this;
  }

  /**
   * Get customerNumber
   *
   * @return customerNumber
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull(message = "Customer number is required")
  @Pattern(regexp = "^[\\w]{1,10}$", message = "The Customer Number should be of length 1 to 10")
  public String getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  public Customer firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   *
   * @return firstName
   */
  @ApiModelProperty(required = true, value = "")
  @NotEmpty(message = "First name is required")
  @Pattern(regexp = "^[a-zA-Z ]{10,50}$", message = "The first name should be of length 10 to 50")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Customer lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   *
   * @return lastName
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @NotEmpty(message = "Last name is required")
  @Pattern(regexp = "[a-zA-Z ]{10,50}$", message = "The last name should be of length 10 to 50")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Customer birthdate(String birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  /**
   * Get birthdate
   *
   * @return birthdate
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull(message = "Date of birth is required")
  @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])\\-(1[0-2]|0[1-9])\\-[0-9]{4}$",
      message = "Date of birth format should be DD-MM-YYYY")
  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public Customer country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   *
   * @return country
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull(message = "Country is required")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Customer countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Get countryCode
   *
   * @return countryCode
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull(message = "Country code is required")
  @Pattern(regexp = "^[A-Za-z]{1,2}$", message = "Country code should be two alphabets")
  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public Customer mobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  /**
   * Get mobileNumber
   *
   * @return mobileNumber
   */
  @ApiModelProperty(required = true, example = "9874561230", value = "")
  @NotNull(message = "Mobile number is required")
  @Pattern(regexp = "^\\d{10}$", message = "Mobile number should contain 10 digits")
  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public Customer email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   *
   * @return email
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull(message = "mail id is required")
  @Email(message = "Mail id is should follow the format of abc@gmail.com")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Customer status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Customer status in the store
   *
   * @return status
   */
  @ApiModelProperty(required = true, value = "Customer status in the store")
  @NotNull(message = "Status is required")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Customer address(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   *
   * @return address
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull(message = "Address is required")
  @Valid
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(this.customerNumber, customer.customerNumber)
        && Objects.equals(this.firstName, customer.firstName)
        && Objects.equals(this.lastName, customer.lastName)
        && Objects.equals(this.birthdate, customer.birthdate)
        && Objects.equals(this.country, customer.country)
        && Objects.equals(this.countryCode, customer.countryCode)
        && Objects.equals(this.mobileNumber, customer.mobileNumber)
        && Objects.equals(this.email, customer.email)
        && Objects.equals(this.status, customer.status)
        && Objects.equals(this.address, customer.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerNumber, firstName, lastName, birthdate, country, countryCode,
        mobileNumber, email, status, address);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Customer {\n");

    sb.append("    customerNumber: ").append(toIndentedString(customerNumber)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    mobileNumber: ").append(toIndentedString(mobileNumber)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
