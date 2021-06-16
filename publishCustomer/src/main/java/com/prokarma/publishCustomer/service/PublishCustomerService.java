package com.prokarma.publishCustomer.service;

import com.prokarma.publishCustomer.model.KafkaCustomer;

public interface PublishCustomerService {
	
	public String postCustomer(KafkaCustomer kafkaCustomer);
}
