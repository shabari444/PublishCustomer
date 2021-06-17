package com.prokarma.publishCustomer.exception;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.prokarma.publishCustomer.model.ErrorResponse;
import com.prokarma.publishCustomer.util.ObjectMapperUtil;
import com.prokarma.publishCustomer.util.PublishCustomerConstants;

@ControllerAdvice
public class PublishCustomerControllerAdvice {

  public static final Logger log = LoggerFactory.getLogger(PublishCustomerControllerAdvice.class);

  private String commonErrorString = "request failed, error response: ";

  private String trasactionId = "transaction-id";

  private ErrorResponse errorResponse = null;

  /**
   * 
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(ServletRequestBindingException.class)
  public ResponseEntity<ErrorResponse> handleException(ServletRequestBindingException ex,
      HttpServletRequest request) {
    errorResponse = new ErrorResponse();
    errorResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    errorResponse.setMessage("Input Headers are mising: " + ex.getMessage());
    errorResponse.setErrorType(InputException.class.getSimpleName());

    log.error(String.format("[%s]%s%s", request.getHeader(trasactionId), commonErrorString,
        ObjectMapperUtil.returnJsonFromObject(errorResponse)));

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   *
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleException(AuthenticationException ex,
      HttpServletRequest request) {
    errorResponse = new ErrorResponse();
    errorResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    errorResponse.setMessage("Token exception: " + ex.getMessage());
    errorResponse.setErrorType(TokenException.class.getSimpleName());
    log.error(String.format("[%s]%s%s", request.getHeader(trasactionId), commonErrorString,
        ObjectMapperUtil.returnJsonFromObject(errorResponse)));
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ErrorResponse> handleException(NoHandlerFoundException ex,
      HttpServletRequest request) {
    errorResponse = new ErrorResponse();
    errorResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    errorResponse.setMessage("General error: " + ex.getMessage());
    errorResponse.setErrorType(GeneralException.class.getSimpleName());
    log.error(String.format("[%s]%s%s", request.getHeader(trasactionId), commonErrorString,
        ObjectMapperUtil.returnJsonFromObject(errorResponse)));
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(GeneralException.class)
  public ResponseEntity<ErrorResponse> handleException(GeneralException ex,
      HttpServletRequest request) {
    errorResponse = new ErrorResponse();
    errorResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    errorResponse.setMessage("General error: " + ex.getMessage());
    errorResponse.setErrorType(GeneralException.class.getSimpleName());
    log.error(String.format("[%s]%s%s", request.getHeader(trasactionId), commonErrorString,
        ObjectMapperUtil.returnJsonFromObject(errorResponse)));
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException ex,
      HttpServletRequest request) {
    Map<String, TreeSet<String>> fieldValidationError = new TreeMap<>();

    List<FieldError> fielderrors = ex.getBindingResult().getFieldErrors();

    for (FieldError fielderror : fielderrors) {

      if (fieldValidationError.containsKey(fielderror.getField())) {
        TreeSet<String> error = fieldValidationError.get(fielderror.getField());
        error.add(fielderror.getDefaultMessage());
        fieldValidationError.put(fielderror.getField(), error);
      } else {
        TreeSet<String> error = new TreeSet<>();
        error.add(fielderror.getDefaultMessage());
        fieldValidationError.put(fielderror.getField(), error);
      }
    }

    errorResponse = new ErrorResponse();
    errorResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    errorResponse.setMessage("Input request validation failed: " + fieldValidationError);
    errorResponse.setErrorType(InputException.class.getSimpleName());
    log.error(String.format("[%s]%s%s", request.getHeader(trasactionId), commonErrorString,
        ObjectMapperUtil.returnJsonFromObject(errorResponse)));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
