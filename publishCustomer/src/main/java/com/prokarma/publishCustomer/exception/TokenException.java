package com.prokarma.publishCustomer.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenException extends AuthenticationException {

  /**
   * 
   */
  private static final long serialVersionUID = -1440746723713531017L;



  public TokenException(String msg) {
    super(msg);
  }

}
