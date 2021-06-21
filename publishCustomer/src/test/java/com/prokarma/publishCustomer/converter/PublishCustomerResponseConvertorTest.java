package com.prokarma.publishCustomer.converter;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
    assertTrue(publishCustomerResponse.equals(publishCustomerResponseConvertor.convert("Success"))
        && publishCustomerResponse.toString()
            .equals(publishCustomerResponseConvertor.convert("Success").toString())
        && publishCustomerResponse.hashCode() == publishCustomerResponseConvertor.convert("Success")
            .hashCode());
    // assertEquals(publishCustomerResponse, publishCustomerResponseConvertor.convert("Success"));

  }

  @Test
  void testConvertFailed() {
    publishCustomerResponse.setMessage(PublishCustomerConstants.FAILURE_MESSAGE);
    publishCustomerResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    assertTrue(publishCustomerResponse
        .equals(publishCustomerResponseConvertor.convert(PublishCustomerConstants.ERRORSTATUS))
        && publishCustomerResponse.toString()
            .equals(publishCustomerResponseConvertor.convert(PublishCustomerConstants.ERRORSTATUS)
                .toString())
        && publishCustomerResponse.hashCode() == publishCustomerResponseConvertor
            .convert(PublishCustomerConstants.ERRORSTATUS).hashCode());
  }

}
