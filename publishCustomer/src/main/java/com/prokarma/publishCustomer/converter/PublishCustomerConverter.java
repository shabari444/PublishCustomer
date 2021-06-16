package com.prokarma.publishCustomer.converter;

import org.springframework.stereotype.Component;
import com.prokarma.publishCustomer.model.Customer;
import com.prokarma.publishCustomer.model.KafkaCustomer;

@Component
public class PublishCustomerConverter {

  public KafkaCustomer convert(Customer customer, String transactionId, String activeId) {
    KafkaCustomer kafkaCustomer = new KafkaCustomer();
    kafkaCustomer.setActiveId(activeId);
    kafkaCustomer.setTransactionId(transactionId);
    kafkaCustomer.setAddress(customer.getAddress());
    kafkaCustomer.setBirthdate(customer.getBirthdate());
    kafkaCustomer.setCountry(customer.getCountry());
    kafkaCustomer.setCountryCode(customer.getCountryCode());
    kafkaCustomer.setCustomerNumber(customer.getCustomerNumber());
    kafkaCustomer.setEmail(customer.getEmail());
    kafkaCustomer.setFirstName(customer.getFirstName());
    kafkaCustomer.setLastName(customer.getLastName());
    kafkaCustomer.setMobileNumber(customer.getMobileNumber());
    kafkaCustomer.setStatus(customer.getStatus());
    return kafkaCustomer;
  }

}
