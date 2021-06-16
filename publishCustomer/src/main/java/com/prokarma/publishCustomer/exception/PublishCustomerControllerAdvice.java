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
public class PublishCustomerControllerAdvice { // extends ResponseEntityExceptionHandler {

  public static final Logger log = LoggerFactory.getLogger(PublishCustomerControllerAdvice.class);

  /**
   * 
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(ServletRequestBindingException.class)
  public ResponseEntity<ErrorResponse> handleException(ServletRequestBindingException ex,
      HttpServletRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    errorResponse.setMessage("Input Headers are mising: " + ex.getMessage());
    errorResponse.setErrorType(InputException.class.getSimpleName());
    log.error("[" + request.getHeader("transaction-id") + "]" + "request failed, error response: "
        + ObjectMapperUtil.returnJsonFromObject(errorResponse));
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
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    errorResponse.setMessage("Token exception: " + ex.getMessage());
    errorResponse.setErrorType(TokenException.class.getSimpleName());
    log.error("[" + request.getHeader("transaction-id") + "]" + "request failed, error response: "
        + ObjectMapperUtil.returnJsonFromObject(errorResponse));
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ErrorResponse> handleException(NoHandlerFoundException ex,
      HttpServletRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    errorResponse.setMessage("General error: " + ex.getMessage());
    errorResponse.setErrorType(GeneralException.class.getSimpleName());
    log.error("[" + request.getHeader("transaction-id") + "]" + "request failed, error response: "
        + ObjectMapperUtil.returnJsonFromObject(errorResponse));
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex,
      HttpServletRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    errorResponse.setMessage("General error: " + ex.getMessage());
    errorResponse.setErrorType(GeneralException.class.getSimpleName());
    log.error("[" + request.getHeader("transaction-id") + "]" + "request failed, error response: "
        + ObjectMapperUtil.returnJsonFromObject(errorResponse));
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(GeneralException.class)
  public ResponseEntity<ErrorResponse> handleException(GeneralException ex,
      HttpServletRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    errorResponse.setMessage("General error: " + ex.getMessage());
    errorResponse.setErrorType(GeneralException.class.getSimpleName());
    log.error("[" + request.getHeader("transaction-id") + "]" + "request failed, error response: "
        + ObjectMapperUtil.returnJsonFromObject(errorResponse));
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

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(PublishCustomerConstants.ERRORSTATUS);
    errorResponse.setMessage("Input request validation failed: " + fieldValidationError);
    errorResponse.setErrorType(InputException.class.getSimpleName());
    log.error("[" + request.getHeader("transaction-id") + "]" + "request failed, error response: "
        + ObjectMapperUtil.returnJsonFromObject(errorResponse));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }


  /**
   * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter
   * is missing.
   *
   * @param ex MissingServletRequestParameterException
   * @param headers HttpHeaders
   * @param status HttpStatus
   * @param request WebRequest
   * @return the ApiException object
   */
  /*
   * @Override protected ResponseEntity<Object> handleMissingServletRequestParameter(
   * MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest
   * request) { String error = ex.getParameterName() + " parameter is missing"; log.error("Error ");
   * return buildResponseEntity(new ApiException(BAD_REQUEST, error, ex)); }
   * 
   *//**
      * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
      *
      * @param ex HttpMediaTypeNotSupportedException
      * @param headers HttpHeaders
      * @param status HttpStatus
      * @param request WebRequest
      * @return the ApiException object
      */
  /*
   * @Override protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
   * HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest
   * request) { StringBuilder builder = new StringBuilder(); builder.append(ex.getContentType());
   * builder.append(" media type is not supported. Supported media types are ");
   * ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", ")); return
   * buildResponseEntity(new ApiException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0,
   * builder.length() - 2), ex)); }
   * 
   *//**
      * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
      *
      * @param ex the MethodArgumentNotValidException that is thrown when @Valid validation fails
      * @param headers HttpHeaders
      * @param status HttpStatus
      * @param request WebRequest
      * @return the ApiException object
      */
  /*
   * @Override protected ResponseEntity<Object>
   * handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
   * HttpStatus status, WebRequest request) { ApiException apiError = new ApiException(BAD_REQUEST);
   * apiError.setMessage("Validation error");
   * apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
   * apiError.addValidationError(ex.getBindingResult().getGlobalErrors()); return
   * buildResponseEntity(apiError); }
   * 
   *//**
      * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
      *
      * @param ex HttpMessageNotReadableException
      * @param headers HttpHeaders
      * @param status HttpStatus
      * @param request WebRequest
      * @return the ApiException object
      */
  /*
   * @Override protected ResponseEntity<Object>
   * handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
   * HttpStatus status, WebRequest request) { ServletWebRequest servletWebRequest =
   * (ServletWebRequest) request; // log.info("{} to {}", servletWebRequest.getHttpMethod(), //
   * servletWebRequest.getRequest().getServletPath()); String error = "Malformed JSON request";
   * return buildResponseEntity(new ApiException(HttpStatus.BAD_REQUEST, error, ex)); }
   * 
   *//**
      * Handle HttpMessageNotWritableException.
      *
      * @param ex HttpMessageNotWritableException
      * @param headers HttpHeaders
      * @param status HttpStatus
      * @param request WebRequest
      * @return the ApiException object
      */
  /*
   * @Override protected ResponseEntity<Object>
   * handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers,
   * HttpStatus status, WebRequest request) { String error = "Error writing JSON output"; return
   * buildResponseEntity(new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, error, ex)); }
   * 
   * @Override protected ResponseEntity<Object>
   * handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus
   * status, WebRequest request) { ApiException apiError = new ApiException(BAD_REQUEST);
   * apiError.setMessage(String.format("Could not find the %s method for URL %s",
   * ex.getHttpMethod(), ex.getRequestURL())); apiError.setDebugMessage(ex.getMessage()); return
   * buildResponseEntity(apiError); }
   * 
   *//**
      * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
      *
      * @param ex the ConstraintViolationException
      * @return the ApiException object
      */
  /*
   * @ExceptionHandler(javax.validation.ConstraintViolationException.class) protected
   * ResponseEntity<Object> handleConstraintViolation( javax.validation.ConstraintViolationException
   * ex) { ApiException apiError = new ApiException(BAD_REQUEST);
   * apiError.setMessage("Validation error");
   * apiError.addValidationErrors(ex.getConstraintViolations()); return
   * buildResponseEntity(apiError); }
   * 
   *//**
      * Handle Exception, handle generic Exception.class
      *
      * @param ex the Exception
      * @return the ApiException object
      *//*
         * @ExceptionHandler(MethodArgumentTypeMismatchException.class) protected
         * ResponseEntity<Object> handleMethodArgumentTypeMismatch(
         * MethodArgumentTypeMismatchException ex, WebRequest request) { ApiException apiError = new
         * ApiException(BAD_REQUEST); apiError.setMessage(
         * String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
         * ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
         * apiError.setDebugMessage(ex.getMessage()); return buildResponseEntity(apiError); }
         * 
         * @ExceptionHandler(AccessDeniedException.class) protected ResponseEntity<Object>
         * handleMethodArgumentTypeMismatch(AccessDeniedException ex, WebRequest request) {
         * ApiException apiError = new ApiException(UNAUTHORIZED);
         * apiError.setMessage(ex.getMessage()); apiError.setDebugMessage(ex.getMessage()); return
         * buildResponseEntity(apiError); }
         * 
         * private ResponseEntity<Object> buildResponseEntity(ApiException apiError) { return new
         * ResponseEntity<>(apiError, apiError.getStatus()); }
         */

}
