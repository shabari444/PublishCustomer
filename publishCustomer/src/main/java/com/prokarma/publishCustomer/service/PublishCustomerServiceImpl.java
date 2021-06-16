package com.prokarma.publishCustomer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prokarma.publishCustomer.model.KafkaCustomer;
import com.prokarma.publishCustomer.publisher.KafkaPublisher;

@Service
public class PublishCustomerServiceImpl implements PublishCustomerService {

  @Autowired
  private KafkaPublisher KafkaPublisher;

  @Override
  public String postCustomer(KafkaCustomer kafkaCustomer) {
    return KafkaPublisher.publishCustometToKafka(kafkaCustomer);


  }
}
