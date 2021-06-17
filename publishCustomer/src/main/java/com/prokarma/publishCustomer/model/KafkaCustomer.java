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

  /*
   * @Override public int hashCode() { final int prime = 31; int result = 1; result = prime * result
   * + ((activeId == null) ? 0 : activeId.hashCode()); result = prime * result + ((address == null)
   * ? 0 : address.hashCode()); result = prime * result + ((birthdate == null) ? 0 :
   * birthdate.hashCode()); result = prime * result + ((country == null) ? 0 : country.hashCode());
   * result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode()); result = prime
   * * result + ((customerNumber == null) ? 0 : customerNumber.hashCode()); result = prime * result
   * + ((email == null) ? 0 : email.hashCode()); result = prime * result + ((firstName == null) ? 0
   * : firstName.hashCode()); result = prime * result + ((lastName == null) ? 0 :
   * lastName.hashCode()); result = prime * result + ((mobileNumber == null) ? 0 :
   * mobileNumber.hashCode()); result = prime * result + ((status == null) ? 0 : status.hashCode());
   * result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode()); return
   * result; }
   * 
   * @Override public boolean equals(Object obj) { if (this == obj) return true; if (obj == null)
   * return false; if (getClass() != obj.getClass()) return false; KafkaCustomer other =
   * (KafkaCustomer) obj; if (activeId == null) { if (other.activeId != null) return false; } else
   * if (!activeId.equals(other.activeId)) return false; if (address == null) { if (other.address !=
   * null) return false; } else if (!address.equals(other.address)) return false; if (birthdate ==
   * null) { if (other.birthdate != null) return false; } else if
   * (!birthdate.equals(other.birthdate)) return false; if (country == null) { if (other.country !=
   * null) return false; } else if (!country.equals(other.country)) return false; if (countryCode ==
   * null) { if (other.countryCode != null) return false; } else if
   * (!countryCode.equals(other.countryCode)) return false; if (customerNumber == null) { if
   * (other.customerNumber != null) return false; } else if
   * (!customerNumber.equals(other.customerNumber)) return false; if (email == null) { if
   * (other.email != null) return false; } else if (!email.equals(other.email)) return false; if
   * (firstName == null) { if (other.firstName != null) return false; } else if
   * (!firstName.equals(other.firstName)) return false; if (lastName == null) { if (other.lastName
   * != null) return false; } else if (!lastName.equals(other.lastName)) return false; if
   * (mobileNumber == null) { if (other.mobileNumber != null) return false; } else if
   * (!mobileNumber.equals(other.mobileNumber)) return false; if (status != other.status) return
   * false; if (transactionId == null) { if (other.transactionId != null) return false; } else if
   * (!transactionId.equals(other.transactionId)) return false; return true; }
   */

  @Override
  public String toString() {
    return "KafkaCustomer [transactionId=" + transactionId + ", activeId=" + activeId
        + ", customerNumber=" + customerNumber + ", firstName=" + firstName + ", lastName="
        + lastName + ", birthdate=" + birthdate + ", country=" + country + ", countryCode="
        + countryCode + ", mobileNumber=" + mobileNumber + ", email=" + email + ", status=" + status
        + ", address=" + address + "]";
  }



}
