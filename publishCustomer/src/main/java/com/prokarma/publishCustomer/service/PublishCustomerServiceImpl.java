package com.prokarma.publishCustomer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prokarma.publishCustomer.model.Customer;
import com.prokarma.publishCustomer.publisher.KafkaPublisher;

@Service
public class PublishCustomerServiceImpl implements PublishCustomerService {

  @Autowired 
  private KafkaPublisher KafkaPublisher;

  @Override
  public Object postCustomer(Customer customer) {
    // TODO Auto-generated method stub
	return KafkaPublisher.publishCustometToKafka(customer);
	  
  
  }
}
