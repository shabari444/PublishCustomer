package com.prokarma.publishCustomer.converter;

import org.springframework.stereotype.Component;
import com.prokarma.publishCustomer.model.Customer;

@Component
public class PublishCustomerMaskConverter {

  public Customer convert(Customer customer) {
    Customer maskedCustomer = new Customer();
    maskedCustomer.setCustomerNumber(getMaskedCustomerNumber(customer.getCustomerNumber()));
    maskedCustomer.setFirstName(customer.getFirstName());
    maskedCustomer.setLastName(customer.getLastName());
    maskedCustomer.setBirthdate(getMaskedDob(customer.getBirthdate()));
    maskedCustomer.setAddress(customer.getAddress());
    maskedCustomer.setCountry(customer.getCountry());
    maskedCustomer.setCountryCode(customer.getCountryCode());
    maskedCustomer.setMobileNumber(customer.getMobileNumber());
    maskedCustomer.setStatus(customer.getStatus());
    maskedCustomer.setEmail(getMaskedEmail(customer.getEmail()));
    return maskedCustomer;
  }

  private String getMaskedCustomerNumber(String customerNumber) {
    StringBuffer sb = new StringBuffer("");
    for (int i = 0; i < customerNumber.length() - 4; i++) {
      sb.append("*");
    }
    sb.append(customerNumber.substring(customerNumber.length() - 4, customerNumber.length()));
    return sb.toString();
  }

  private String getMaskedDob(String dob) {
    StringBuffer sb = new StringBuffer("**-**-");
    sb.append(dob.substring(dob.length() - 4, dob.length()));
    return sb.toString();
  }

  private String getMaskedEmail(String email) {
    StringBuffer sb = new StringBuffer(email.substring(0, 3));
    int reqIndex = email.indexOf("@");
    for (int i = 3; i < reqIndex; i++) {
      sb.append("*");
    }
    sb.append(email.substring(reqIndex, email.length()));
    
    return sb.toString();
  }
}
