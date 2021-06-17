package com.prokarma.publishCustomer.model;

import java.io.Serializable;
import com.prokarma.publishCustomer.model.Customer.StatusEnum;

public class KafkaCustomer implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -1100227914817526978L;


  private String transactionId;


  private String activeId;


  private String customerNumber = null;


  private String firstName = null;

  private String lastName = null;

  private String birthdate = null;

  private String country = null;

  private String countryCode = null;

  private String mobileNumber = null;

  private String email = null;


  private StatusEnum status = null;

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  private Address address = null;

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getActiveId() {
    return activeId;
  }

  public void setActiveId(String activeId) {
    this.activeId = activeId;
  }

  public String getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }



  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }



  @Override
  public String toString() {
    return "KafkaCustomer [transactionId=" + transactionId + ", activeId=" + activeId
        + ", customerNumber=" + customerNumber + ", firstName=" + firstName + ", lastName="
        + lastName + ", birthdate=" + birthdate + ", country=" + country + ", countryCode="
        + countryCode + ", mobileNumber=" + mobileNumber + ", email=" + email + ", status=" + status
        + ", address=" + address + "]";
  }



}
