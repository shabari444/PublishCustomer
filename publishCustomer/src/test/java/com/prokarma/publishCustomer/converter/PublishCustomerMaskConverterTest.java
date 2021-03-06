package com.prokarma.publishCustomer.converter;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prokarma.publishCustomer.model.Address;
import com.prokarma.publishCustomer.model.Customer;
import com.prokarma.publishCustomer.model.Customer.StatusEnum;

@Tag("unit")
class PublishCustomerMaskConverterTest {

  private PublishCustomerMaskConverter publishCustomerConverter;

  private Customer customer;

  private Customer maskedCustomer;

  @BeforeEach
  public void setup() {
    publishCustomerConverter = new PublishCustomerMaskConverter();
    StatusEnum statusEnum = StatusEnum.OPEN;
    Address address = new Address();
    customer = new Customer();
    address.setAddressLine1("Hydearabad");
    address.setPostalCode("50001");
    customer.setAddress(address);
    customer.birthdate("19-08-1994");
    customer.country("India");
    customer.countryCode("IN");
    customer.customerNumber("PK12345678");
    customer.email("ehsabarish@pkglobal.com");
    customer.firstName("Shabarish Kumar");
    customer.lastName("Elluru");
    customer.mobileNumber("9871235670");
    customer.setStatus(statusEnum);
    maskedCustomer = new Customer();
    maskedCustomer.setAddress(address);
    maskedCustomer.birthdate("**-**-1994");
    maskedCustomer.country("India");
    maskedCustomer.countryCode("IN");
    maskedCustomer.customerNumber("******5678");
    maskedCustomer.email("ehs*******@pkglobal.com");
    maskedCustomer.firstName("Shabarish Kumar");
    maskedCustomer.lastName("Elluru");
    maskedCustomer.mobileNumber("9871235670");
    maskedCustomer.setStatus(statusEnum);
  }


  @Test
  void testGetMaskedCustomerNumber() {
    assertTrue(maskedCustomer.equals(publishCustomerConverter.convert(customer)) && 
        maskedCustomer.toString().equals(publishCustomerConverter.convert(customer).toString()) &&
        maskedCustomer.hashCode()==publishCustomerConverter.convert(customer).hashCode());
  }



}
