package com.meal.controller;

import com.meal.service.Exception.SecureException;
import com.meal.service.Exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
      String bodyOfResponse = ex.getMessage();
      if(bodyOfResponse == null) {
        bodyOfResponse = "Bad request";
      }
      return handleExceptionInternal(ex, bodyOfResponse,
              new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {SecureException.class})
    protected ResponseEntity<Object> handleSecureConflict(RuntimeException ex, WebRequest request) {
      String bodyOfResponse = ex.getMessage();
      if(bodyOfResponse == null) {
        bodyOfResponse = "Forbidden";
      }
      return handleExceptionInternal(ex, bodyOfResponse,
              new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

  @ExceptionHandler(value = {ServiceException.class})
  protected ResponseEntity<Object> handleServiceConflict(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = ex.getMessage();
    if(bodyOfResponse == null) {
      bodyOfResponse = "BadRequest";
    }
    return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}