package com.prokarma.publishCustomer.exception;

import org.springframework.web.bind.ServletRequestBindingException;

public class InputException extends ServletRequestBindingException {

  /**
   * 
   */
  private static final long serialVersionUID = 2634496238593952661L;

  public InputException(String msg) {
    super(msg);
  }



}
