package com.prokarma.publishCustomer.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prokarma.publishCustomer.model.PublishCustomerResponse;
import com.prokarma.publishCustomer.util.PublishCustomerConstants;

@Tag("unit")
class PublishCustomerResponseConvertorTest {

  private PublishCustomerResponse publishCustomerResponse;

  private PublishCustomerResponseConvertor publishCustomerResponseConvertor;

  @BeforeEach
  public void setup() {
    publishCustomerResponseConvertor = new PublishCustomerResponseConvertor();
    publishCustomerResponse = new PublishCustomerResponse();

  }

  @Test
  void testConvertSuccess() {
    publishCustomerResponse.setMessage(PublishCustomerConstants.SUCCESS_MESSAGE);
    publishCustomerResponse.setStatus(PublishCustomerConstants.SUCCESS);
    assertEquals(publishCustomerResponse.toString(),
        publishCustomerResponseConvertor.convert("Success").toString());
  }

  @Test
  void testConvertFailed() {
    publishCustomerResponse.setMessage(PublishCustomerConstants.FAILURE_MESSAGE);
    publishCustomerResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    assertEquals(publishCustomerResponse.toString(),
        publishCustomerResponseConvertor.convert(PublishCustomerConstants.ERRORSTATUS).toString());
  }

}
