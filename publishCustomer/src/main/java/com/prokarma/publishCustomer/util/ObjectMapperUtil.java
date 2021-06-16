package com.prokarma.publishCustomer.util;



import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



// @Configuration
public class ObjectMapperUtil {
  private static final Logger log = LoggerFactory.getLogger(ObjectMapperUtil.class);

  public static String returnJsonFromObject(Object dataSet) {
    String jsonObjectAsString = null;
    try {
      jsonObjectAsString = new ObjectMapper().writeValueAsString(dataSet);
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return jsonObjectAsString;
  }
}
