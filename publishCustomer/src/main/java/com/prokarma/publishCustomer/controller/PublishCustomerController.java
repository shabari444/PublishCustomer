package com.prokarma.publishCustomer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prokarma.publishCustomer.model.Customer;
import com.prokarma.publishCustomer.service.PublishCustomerService;

@RestController
@RequestMapping("/api")
@Validated
public class PublishCustomerController {
	
	private Logger logger = LoggerFactory.getLogger(PublishCustomerController.class);
	
	@Autowired
	private PublishCustomerService  publishCustomerService;
	
	@GetMapping("/get")
	public String run() {
		return "running";
	}
	@PostMapping("/publish")
	public Object postCutsomer(@Valid @RequestBody(required = true) Customer customer) {
		logger.info("request info "+customer);
		 return publishCustomerService.postCustomer(customer);
	}
} 
