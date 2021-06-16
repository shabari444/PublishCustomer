package com.prokarma.publishCustomer.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.prokarma.publishCustomer.model.KafkaCustomer;
import com.prokarma.publishCustomer.util.ObjectMapperUtil;

@Component
public class KafkaPublisher {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Value("${topic}")
  private String topic;

  public String publishCustometToKafka(KafkaCustomer publishCustomer) {

    kafkaTemplate.send(topic, ObjectMapperUtil.returnJsonFromObject(publishCustomer));

    return "Success";

  }
}
