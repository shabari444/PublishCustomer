
package com.prokarma.publishCustomer.publisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.concurrent.ListenableFuture;
import com.prokarma.publishCustomer.PublishCustomerApplication;
import com.prokarma.publishCustomer.model.KafkaCustomer;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = PublishCustomerApplication.class)

@WebAppConfiguration
class KafkaPublisherTest {

  @MockBean
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private KafkaPublisher kafkaPublisher;


  @SuppressWarnings("unchecked")
  @Test
  void testpublishCustometToKafka() {

    when(kafkaTemplate.send(Mockito.any(String.class), Mockito.any(String.class)))
        .thenReturn(mock(ListenableFuture.class));
    assertEquals("Success", kafkaPublisher.publishCustometToKafka(new KafkaCustomer()));

  }
}

