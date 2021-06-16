package com.prokarma.publishCustomer.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
public class PublishCustomerMaskConverterTest {

  private PublishCustomerMaskConverter publishCustomerConverter;

  @BeforeEach
  public void setup() {
    publishCustomerConverter = new PublishCustomerMaskConverter();
  }

  /*
   * @Test public void testGetMaskedCustomerNumber() { assertEquals("****2345",
   * publishCustomerConverter.getMaskedCustomerNumber("Pro12345")); }
   * 
   * @Test public void testGetMaskedEmail() { assertEquals("abc***@gmail.com",
   * publishCustomerConverter.getMaskedEmail("abcXyz@gmail.com")); }
   */
}
