package com.prokarma.publishCustomer.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.prokarma.publishCustomer.model.Customer;

@Component
public class KafkaPublisher {

	@Autowired
	private KafkaTemplate<String, Customer> kafkaTemplate;

	public static final String TOPIC = "CREATE_CUSTOMER";

	public Object publishCustometToKafka(Customer customer) {

		kafkaTemplate.send(TOPIC, customer);

		return "Success";

	}
}
