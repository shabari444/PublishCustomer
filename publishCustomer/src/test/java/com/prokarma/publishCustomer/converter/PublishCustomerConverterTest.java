package com.prokarma.publishCustomer.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import com.prokarma.publishCustomer.model.Address;
import com.prokarma.publishCustomer.model.Customer;
import com.prokarma.publishCustomer.model.Customer.StatusEnum;
import com.prokarma.publishCustomer.model.KafkaCustomer;


@Tag("unit")
public class PublishCustomerConverterTest {

  private PublishCustomerConverter publishCustomerConverter;

  private KafkaCustomer kafkaCustomer = null;

  private Customer customer = null;

  @BeforeEach
  public void setUp() {
    publishCustomerConverter = new PublishCustomerConverter();
    kafkaCustomer = new KafkaCustomer();
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

    kafkaCustomer.setAddress(address);
    kafkaCustomer.setBirthdate("19-08-1994");
    kafkaCustomer.setCountry("India");
    kafkaCustomer.setCountryCode("IN");
    kafkaCustomer.setCustomerNumber("PK12345678");
    kafkaCustomer.setEmail("ehsabarish@pkglobal.com");
    kafkaCustomer.setFirstName("Shabarish Kumar");
    kafkaCustomer.setLastName("Elluru");
    kafkaCustomer.setMobileNumber("9871235670");
    kafkaCustomer.setStatus(statusEnum);
    kafkaCustomer.setActiveId("testActiveId");
    kafkaCustomer.setTransactionId("testTransactionId");

  }


  @Test
  public void testconvert() {
    assertEquals(kafkaCustomer.toString(),
        publishCustomerConverter.convert(customer, "testTransactionId", "testActiveId").toString());
  }

}
