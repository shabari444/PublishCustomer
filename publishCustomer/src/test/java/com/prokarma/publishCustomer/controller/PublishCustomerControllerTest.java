package com.prokarma.publishCustomer.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import com.prokarma.publishCustomer.PublishCustomerApplication;
import com.prokarma.publishCustomer.converter.PublishCustomerConverter;
import com.prokarma.publishCustomer.converter.PublishCustomerMaskConverter;
import com.prokarma.publishCustomer.converter.PublishCustomerResponseConvertor;
import com.prokarma.publishCustomer.model.Address;
import com.prokarma.publishCustomer.model.Customer;
import com.prokarma.publishCustomer.model.Customer.StatusEnum;
import com.prokarma.publishCustomer.model.KafkaCustomer;
import com.prokarma.publishCustomer.model.PublishCustomerResponse;
import com.prokarma.publishCustomer.service.PublishCustomerService;
import com.prokarma.publishCustomer.util.ObjectMapperUtil;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = PublishCustomerApplication.class)
public class PublishCustomerControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;
  private static final String CLIENT_ID = "qerwteyghsr";
  private static final String CLIENT_SECRET = "1234$#@!";

  private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

  @Autowired
  private FilterChainProxy springSecurityFilterChain;

  private Customer customer;

  @BeforeEach
  public void setUp() {
    this.mockMvc =
        MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    StatusEnum statusEnum = StatusEnum.OPEN;
    Address address = new Address();
    /*
     * { "customerNumber": "PK123456", "firstName": "Shabarish Kumar", "lastName":
     * "Shabarish Kumar", "birthdate": "19-08-1995", "country": "India", "countryCode": "IN",
     * "mobileNumber": "9874561230", "email": "abc@gmail.com", "status": "Restored", "address": {
     * "addressLine1": "gtyu", "addressLine2": "string", "street": "string", "postalCode":"51577" }
     * }
     */
    customer = new Customer();
    address.setAddressLine1("Hyderabad");
    address.setPostalCode("50001");
    address.setAddressLine2("2nd line");
    address.setStreet("my Street");
    customer.setAddress(address);
    customer.birthdate("19-08-1994");
    customer.country("India");
    customer.countryCode("IN");
    customer.customerNumber("PK12345678");
    customer.email("ehsabarish@pkglobal.com");
    customer.firstName("Shabarish Kumar");
    customer.lastName("Shabarish Elluru");
    customer.mobileNumber("9871235670");
    customer.setStatus(statusEnum);
  }


  @MockBean
  private PublishCustomerService publishCustomerService;

  @MockBean
  private PublishCustomerMaskConverter publishCustomerMaskConverter;

  @MockBean
  private PublishCustomerConverter publishCustomerConverter;

  @MockBean
  private PublishCustomerResponseConvertor publishCustomerResponseConvertor;

  private PublishCustomerResponse mockResponse = new PublishCustomerResponse();

  private ResponseEntity<PublishCustomerResponse> responseEntity = null;

  private KafkaCustomer kafkaCustomer = new KafkaCustomer();

  @Test
  public void testPublishCustomer() throws Exception {

    mockResponse.setStatus("Success");
    mockResponse.setMessage("Customer has been published successfully");

    responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);


    Mockito.when(publishCustomerService.postCustomer(kafkaCustomer)).thenReturn("Success");


    String accessToken = obtainAccessToken("prokarma", "pkSoftech");

    System.out.println(ObjectMapperUtil.returnJsonFromObject(customer));


    mockMvc.perform(post("/api/publish").header("Authorization", "Bearer " + accessToken)
        .header("Transaction-Id", "testTransactionId").header("Activity-Id", "testActivityId")
        .contentType(CONTENT_TYPE)
        .content(ObjectMapperUtil.returnJsonFromObject(customer).replaceAll("OPEN", "Open"))
        .accept(CONTENT_TYPE)).andExpect(status().isOk());



    /*
     * MvcResult result = mockMvc.perform(requestBuilder).andReturn();
     * 
     * System.out.println(result.getResponse()); String expected = "Success";
     * 
     * // {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K //
     * Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}
     * 
     * // assertEquals("Success",;
     * 
     * JSONAssert.assertEquals(responseEntity.toString(), result.getResponse().getContentAsString(),
     * false);
     */
  }

  private String obtainAccessToken(String username, String password) throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "password");
    params.add("client_id", CLIENT_ID);
    params.add("username", username);
    params.add("password", password);

    // @formatter:off

    ResultActions result = mockMvc.perform(post("/oauth/token")
                           .params(params)
                           .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                           .accept(CONTENT_TYPE))
                           .andExpect(status().isOk())
                           .andExpect(content().contentType(CONTENT_TYPE));
    
    // @formatter:on

    String resultString = result.andReturn().getResponse().getContentAsString();

    JacksonJsonParser jsonParser = new JacksonJsonParser();
    return jsonParser.parseMap(resultString).get("access_token").toString();
  }

}
