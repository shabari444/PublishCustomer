package com.prokarma.publishCustomer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
	
	
	@PostMapping("/publish")
	public Object postCutsomer(@Valid @RequestBody(required = true) Customer customer) {
		logger.info("request info "+customer);
		if("Success".equalsIgnoreCase((String)publishCustomerService.postCustomer(customer)))
		 return new ResponseEntity<>("Customer has been published Successfully ", HttpStatus.CREATED);
		else
			 return new ResponseEntity<>("Failed to publish the  Customer ", HttpStatus.BAD_REQUEST);	
	}
} 
