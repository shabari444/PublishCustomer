package com.prokarma.publishCustomer.converter;

import org.springframework.stereotype.Component;
import com.prokarma.publishCustomer.model.PublishCustomerResponse;

@Component
public class PublishCustomerResponseConvertor {

  public static final String SUCCESS = "SUCCESS";

  public PublishCustomerResponse convert(String message) {
    PublishCustomerResponse publishCustomerResponse = new PublishCustomerResponse();
    if (SUCCESS.equalsIgnoreCase(message)) {
      publishCustomerResponse.setMessage("Customer has been published successfully");
      publishCustomerResponse.setStatus("Success");
    } else {
      publishCustomerResponse.setMessage("Failed to publish the customer");
      publishCustomerResponse.setStatus("Failed");
    }
    return publishCustomerResponse;
  }

}
