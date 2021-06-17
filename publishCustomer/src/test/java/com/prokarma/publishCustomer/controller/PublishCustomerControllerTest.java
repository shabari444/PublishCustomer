package com.prokarma.publishCustomer.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
  private static final String USERNAME = "prokarma";
  private static final String PASSWORD = "pkSoftech";

  private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

  private static final String INVALID_TOKEN =
      "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjM5MzU4NjcsInVzZXJfbmFtZSI6InByb2thcm1hIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjEzNmFmMjUwLTU1NTUtNGRjOS1hNzUxLTBlNDZlZTE3MWY5ZiIsImNsaWVudF9pZCI6InFlcnd0ZXlnaHNyIiwic2NvcGUiOlsiYWxsIl19.U8m0NxKaP4vsvtoZv0ol47jL17-pF5XPpLhml2KMpxE";

  @Autowired
  private FilterChainProxy springSecurityFilterChain;

  private Customer validCustomer;

  private Customer inValidCustomer;

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @BeforeEach
  public void setUp() {
    this.mockMvc =
        MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
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
    inValidCustomer = new Customer();
    invalidAddress.setAddressLine1("Hyderabad*7^%7");
    invalidAddress.setPostalCode("50000*651");
    invalidAddress.setAddressLine2("2nd line");
    invalidAddress.setStreet("my Street");
    inValidCustomer.setAddress(address);
    inValidCustomer.birthdate("198-08-1994");
    inValidCustomer.country("Ind*7ia");
    inValidCustomer.countryCode("IN8");
    inValidCustomer.customerNumber("PK12345678()");
    inValidCustomer.email("ehsabarishpkglobal.com");
    inValidCustomer.firstName("Shabari");
    inValidCustomer.lastName("Elluru");
    inValidCustomer.mobileNumber("987123567098");
  }


  @MockBean
  private PublishCustomerService publishCustomerService;

  @MockBean
  private PublishCustomerMaskConverter publishCustomerMaskConverter;

  @MockBean
  private PublishCustomerConverter publishCustomerConverter;

  @MockBean
  private PublishCustomerResponseConvertor publishCustomerResponseConvertor;


  private KafkaCustomer kafkaCustomer = new KafkaCustomer();

  @Test
  public void testPublishCustomerSuccessCase() throws Exception {


    String accessToken = obtainAccessToken(USERNAME, PASSWORD);


    mockMvc.perform(post("/api/publish").header("Authorization", "Bearer " + accessToken)
        .header("Transaction-Id", "testTransactionId").header("Activity-Id", "testActivityId")
        .contentType(CONTENT_TYPE)
        .content(ObjectMapperUtil.returnJsonFromObject(validCustomer).replaceAll("OPEN", "Open"))
        .accept(CONTENT_TYPE)).andExpect(status().isOk());

  }

  @Test
  public void testPublishCustomerAuthenticationfailed() throws Exception {

    Mockito.when(publishCustomerService.postCustomer(kafkaCustomer)).thenReturn("Success");


    System.out.println(ObjectMapperUtil.returnJsonFromObject(inValidCustomer));


    mockMvc.perform(post("/api/publish").header("Authorization", "Bearer " + INVALID_TOKEN)
        .header("Transaction-Id", "testTransactionId").header("Activity-Id", "testActivityId")
        .contentType(CONTENT_TYPE)
        .content(ObjectMapperUtil.returnJsonFromObject(inValidCustomer).replaceAll("OPEN", "Open"))
        .accept(CONTENT_TYPE)).andExpect(status().isUnauthorized());

  }

  @Test
  public void testPublishCustomerInvalidInput() throws Exception {

    Mockito.when(publishCustomerService.postCustomer(kafkaCustomer)).thenReturn("Success");


    System.out.println(ObjectMapperUtil.returnJsonFromObject(inValidCustomer));


    mockMvc.perform(post("/api/publish")
        .header("Authorization", "Bearer " + obtainAccessToken(USERNAME, PASSWORD))
        .header("Transaction-Id", "testTransactionId").header("Activity-Id", "testActivityId")
        .contentType(CONTENT_TYPE)
        .content(ObjectMapperUtil.returnJsonFromObject(inValidCustomer).replaceAll("OPEN", "Open"))
        .accept(CONTENT_TYPE)).andExpect(status().isBadRequest());

  }

  @Test
  public void testPublishCustomerNoHandlerfound() throws Exception {

    Mockito.when(publishCustomerService.postCustomer(kafkaCustomer)).thenReturn("Success");


    System.out.println(ObjectMapperUtil.returnJsonFromObject(inValidCustomer));


    mockMvc.perform(post("/api/get")
        .header("Authorization", "Bearer " + obtainAccessToken(USERNAME, PASSWORD))
        .header("Transaction-Id", "testTransactionId").header("Activity-Id", "testActivityId")
        .contentType(CONTENT_TYPE)
        .content(ObjectMapperUtil.returnJsonFromObject(inValidCustomer).replaceAll("OPEN", "Open"))
        .accept(CONTENT_TYPE)).andExpect(status().isNotFound());

  }

  @Test
  public void testPublishCustomerHeadersMissing() throws Exception {

    Mockito.when(publishCustomerService.postCustomer(kafkaCustomer)).thenReturn("Success");


    System.out.println(ObjectMapperUtil.returnJsonFromObject(inValidCustomer));


    mockMvc.perform(post("/api/publish")
        .header("Authorization", "Bearer " + obtainAccessToken(USERNAME, PASSWORD))
        .header("Activity-Id", "testActivityId").contentType(CONTENT_TYPE)
        .content(ObjectMapperUtil.returnJsonFromObject(inValidCustomer).replaceAll("OPEN", "Open"))
        .accept(CONTENT_TYPE)).andExpect(status().isBadRequest());

  }


  /*
   * @Test public void testPublishCustomerInternalServerError() throws Exception {
   * 
   * Mockito.when(publishCustomerService.postCustomer(kafkaCustomer)).thenReturn("Success");
   * 
   * 
   * System.out.println(ObjectMapperUtil.returnJsonFromObject(inValidCustomer));
   * 
   * exception.expect(GeneralException.class);
   * 
   * mockMvc.perform(post("/api/publish") .header("Authorization", "Bearer " +
   * obtainAccessToken(USERNAME, PASSWORD)) .header("Transaction-Id",
   * "testTransactionId").header("Activity-Id", "testActivityId") .contentType(CONTENT_TYPE)
   * .content(ObjectMapperUtil.returnJsonFromObject(inValidCustomer))
   * .accept(CONTENT_TYPE)).andExpect(status().isInternalServerError());
   * 
   * }
   */



  private String obtainAccessToken(String username, String password) throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "password");
    params.add("client_id", CLIENT_ID);
    params.add("username", username);
    params.add("password", password);

    ResultActions result = mockMvc
        .perform(post("/oauth/token").params(params).with(httpBasic(CLIENT_ID, CLIENT_SECRET))
            .accept(CONTENT_TYPE))
        .andExpect(status().isOk()).andExpect(content().contentType(CONTENT_TYPE));

    // @formatter:on

    String resultString = result.andReturn().getResponse().getContentAsString();

    JacksonJsonParser jsonParser = new JacksonJsonParser();
    return jsonParser.parseMap(resultString).get("access_token").toString();
  }

}
