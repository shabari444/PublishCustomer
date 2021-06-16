package com.prokarma.publishCustomer.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenException extends AuthenticationException {

  /**
   * 
   */
  private static final long serialVersionUID = -1440746723713531017L;

  public TokenException(String msg, Throwable t) {
    super(msg, t);
    // TODO Auto-generated constructor stub
  }

  public TokenException(String msg) {
    super(msg);
    // TODO Auto-generated constructor stub
  }

}
