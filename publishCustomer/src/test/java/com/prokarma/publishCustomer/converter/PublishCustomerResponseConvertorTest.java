package com.prokarma.publishCustomer.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prokarma.publishCustomer.model.PublishCustomerResponse;
import com.prokarma.publishCustomer.util.PublishCustomerConstants;

@Tag("unit")
public class PublishCustomerResponseConvertorTest {

  private PublishCustomerResponse publishCustomerResponse;

  private PublishCustomerResponseConvertor publishCustomerResponseConvertor;

  @BeforeEach
  public void setup() {
    publishCustomerResponseConvertor = new PublishCustomerResponseConvertor();
    publishCustomerResponse = new PublishCustomerResponse();
    publishCustomerResponse.setMessage(PublishCustomerConstants.SUCCESS_MESSAGE);
    publishCustomerResponse.setStatus(PublishCustomerConstants.SUCCESS);
  }

  @Test
  public void testConvert() {
    assertEquals(publishCustomerResponse.toString(),
        publishCustomerResponseConvertor.convert("Success").toString());
  }
}
