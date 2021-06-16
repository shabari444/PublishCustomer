package com.prokarma.publishCustomer.exception;

import org.springframework.web.bind.ServletRequestBindingException;

public class InputException extends ServletRequestBindingException {

  public InputException(String msg) {
    super(msg);
    // TODO Auto-generated constructor stub
  }

  /**
   * 
   */
  private static final long serialVersionUID = 2634496238593952661L;

 
}
