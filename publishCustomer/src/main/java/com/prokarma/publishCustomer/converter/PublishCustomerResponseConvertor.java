package com.prokarma.publishCustomer.converter;

import org.springframework.stereotype.Component;
import com.prokarma.publishCustomer.model.PublishCustomerResponse;
import com.prokarma.publishCustomer.util.PublishCustomerConstants;

@Component
public class PublishCustomerResponseConvertor {


  public PublishCustomerResponse convert(String message) {
    PublishCustomerResponse publishCustomerResponse = new PublishCustomerResponse();
    if (PublishCustomerConstants.SUCCESS.equalsIgnoreCase(message)) {
      publishCustomerResponse.setMessage(PublishCustomerConstants.SUCCESS_MESSAGE);
      publishCustomerResponse.setStatus(PublishCustomerConstants.SUCCESS);
    } else {
      publishCustomerResponse.setMessage(PublishCustomerConstants.FAILURE_MESSAGE);
      publishCustomerResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    }
    return publishCustomerResponse;
  }

}
