package com.prokarma.publishCustomer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import com.prokarma.publishCustomer.model.Address;
import com.prokarma.publishCustomer.model.Customer;
import com.prokarma.publishCustomer.model.Customer.StatusEnum;
import com.prokarma.publishCustomer.util.PublishCustomerConstants;


@SpringBootTest(classes = PublishCustomerApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @WebAppConfiguration
class PublishCustomerApplicationTests {

  @LocalServerPort
  private int port;

  TestRestTemplate restTemplate = new TestRestTemplate();

  private static final String CLIENT_ID = "qerwteyghsr";
  private static final String CLIENT_SECRET = "1234$#@!";
  private static final String USERNAME = "prokarma";
  private static final String PASSWORD = "pkSoftech";
  private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
  private Customer validCustomer;

  private Customer invalidCustomer;

  @BeforeEach
  public void setUp() {
    StatusEnum statusEnum = StatusEnum.OPEN;
    Address address = new Address();
    Address invalidAddress = new Address();
    validCustomer = new Customer();
    address.setAddressLine1("Hyderabad");
    address.setPostalCode("50001");
    address.setAddressLine2("2nd line");
    address.setStreet("my Street");
    validCustomer.setAddress(address);
    validCustomer.birthdate("19-08-1994");
    validCustomer.country("India");
    validCustomer.countryCode("IN");
    validCustomer.customerNumber("PK12345678");
    validCustomer.email("ehsabarish@pkglobal.com");
    validCustomer.firstName("Shabarish Kumar");
    validCustomer.lastName("Shabarish Elluru");
    validCustomer.mobileNumber("9871235670");
    validCustomer.setStatus(statusEnum);
    invalidCustomer = new Customer();
    invalidAddress.setAddressLine1("Hyderabad*7^%7");
    invalidAddress.setPostalCode("50000*651");
    invalidAddress.setAddressLine2("2nd line");
    invalidAddress.setStreet("my Street");
    invalidCustomer.setAddress(address);
    invalidCustomer.birthdate("198-08-1994");
    invalidCustomer.country("Ind*7ia");
    invalidCustomer.countryCode("IN8");
    invalidCustomer.customerNumber("PK12345678()");
    invalidCustomer.email("ehsabarishpkglobal.com");
    invalidCustomer.firstName("Shabari");
    invalidCustomer.lastName("Elluru");
    invalidCustomer.mobileNumber("987123567098");
  }


  @Test
  void integrationTestPublishSuccess() throws Exception {

    String accessToken = obtainTokenForIntgernationTest();
    // System.out.println(accessToken);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Authorization", "Bearer " + accessToken);
    httpHeaders.add("Transaction-Id", "integrationTestTransactionId");
    httpHeaders.add("Activity-Id", "integrationActivityId");
    httpHeaders.add("Content-Type", CONTENT_TYPE);

    HttpEntity<Customer> httpEntity = new HttpEntity<>(validCustomer, httpHeaders);

    ResponseEntity<String> responseEntity = restTemplate
        .postForEntity(createURLWithPort("/api/publish"), httpEntity, String.class, httpHeaders);

    assertTrue(responseEntity.getBody().contains(PublishCustomerConstants.SUCCESS_MESSAGE));
  }

  @Test
  void integrationTestPublishInvalidData() throws Exception {

    String accessToken = obtainTokenForIntgernationTest();
    // System.out.println(accessToken);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Authorization", "Bearer " + accessToken);
    httpHeaders.add("Transaction-Id", "integrationTestTransactionId");
    httpHeaders.add("Activity-Id", "integrationActivityId");
    httpHeaders.add("Content-Type", CONTENT_TYPE);

    HttpEntity<Customer> httpEntity = new HttpEntity<>(invalidCustomer, httpHeaders);

    ResponseEntity<String> responseEntity = restTemplate
        .postForEntity(createURLWithPort("/api/publish"), httpEntity, String.class, httpHeaders);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

  private String obtainTokenForIntgernationTest() {
    ResponseEntity<String> response = null;

    String credentials = CLIENT_ID + ":" + CLIENT_SECRET;

    @SuppressWarnings("deprecation")
    String encodedCredentials = new String(Base64.encode(credentials.getBytes()));

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.add("Authorization", "Basic " + encodedCredentials);

    HttpEntity<String> request = new HttpEntity<String>(headers);

    String access_token_url = createURLWithPort("/oauth/token");
    access_token_url += "?username=" + USERNAME;
    access_token_url += "&password=" + PASSWORD;
    access_token_url += "&grant_type=password";

    response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);
    System.out.println(response.toString());
    // "http://localhost:8081/oauth/token?username=prokarma&password=pkSoftech&grant_type=password"resposn
    String resposneBody = response.getBody();
    JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
    return jacksonJsonParser.parseMap(resposneBody).get("access_token").toString();


  }
}
