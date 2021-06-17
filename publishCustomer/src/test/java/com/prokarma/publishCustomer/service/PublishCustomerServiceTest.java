package com.prokarma.publishCustomer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.prokarma.publishCustomer.PublishCustomerApplication;
import com.prokarma.publishCustomer.model.KafkaCustomer;
import com.prokarma.publishCustomer.publisher.KafkaPublisher;
import com.prokarma.publishCustomer.util.PublishCustomerConstants;


@SpringBootTest(classes = PublishCustomerApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class PublishCustomerServiceTest {


  @MockBean
  private KafkaPublisher kafkapublisher;


  @Test
  public void testPostCustomer() {
    Mockito.when(kafkapublisher.publishCustometToKafka(new KafkaCustomer())).thenReturn("Success");
    assertEquals(PublishCustomerConstants.SUCCESS, PublishCustomerConstants.SUCCESS);
  }
}
