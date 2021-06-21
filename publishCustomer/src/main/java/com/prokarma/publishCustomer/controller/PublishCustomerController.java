package com.prokarma.publishCustomer.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prokarma.publishCustomer.converter.PublishCustomerConverter;
import com.prokarma.publishCustomer.converter.PublishCustomerMaskConverter;
import com.prokarma.publishCustomer.converter.PublishCustomerResponseConvertor;
import com.prokarma.publishCustomer.model.Customer;
import com.prokarma.publishCustomer.model.KafkaCustomer;
import com.prokarma.publishCustomer.model.PublishCustomerResponse;
import com.prokarma.publishCustomer.service.PublishCustomerService;
import com.prokarma.publishCustomer.util.ObjectMapperUtil;

@RestController
@RequestMapping("/api")
@Validated
public class PublishCustomerController {

  private Logger logger = LoggerFactory.getLogger(PublishCustomerController.class);

  @Autowired
  private PublishCustomerService publishCustomerService;

  @Autowired
  private PublishCustomerMaskConverter publishCustomerMaskConverter;

  @Autowired
  private PublishCustomerConverter publishCustomerConverter;

  @Autowired
  private PublishCustomerResponseConvertor publishCustomerResponseConvertor;



  @PostMapping(value = "/publish", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PublishCustomerResponse> postCutsomer(
      @RequestHeader(value = "Authorization", required = true) String authorization,
      @RequestHeader(value = "Transaction-Id", required = true) String transactionId,
      @RequestHeader(value = "Activity-Id", required = true) String activeId,
      @Valid @RequestBody(required = true) Customer customer) {



    logger.info(String.format("[%s]Incoming request info:%s", transactionId,
        ObjectMapperUtil.returnJsonFromObject(publishCustomerMaskConverter.convert(customer))));

    KafkaCustomer kafkaCustomer =
        publishCustomerConverter.convert(customer, transactionId, activeId);

    String message = publishCustomerService.postCustomer(kafkaCustomer);


    PublishCustomerResponse publishCustomerResponse =
        publishCustomerResponseConvertor.convert(message);


    logger.info(String.format("[%s]Outgoing response:%s", transactionId,
        ObjectMapperUtil.returnJsonFromObject(publishCustomerResponse)));

    return new ResponseEntity<>(publishCustomerResponse, HttpStatus.OK);
  }
}
