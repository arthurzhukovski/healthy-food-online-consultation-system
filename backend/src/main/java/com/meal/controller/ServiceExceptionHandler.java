package com.meal.controller;

import com.meal.entity.ExceptionEntity;
import com.meal.service.Exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ ServiceException.class })
  public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {

    String bodyOfResponse = ex.getMessage();
    if(bodyOfResponse == null) {
      bodyOfResponse  = "Request is invalid";
    }
    return handleExceptionInternal(ex, createResponseMessage(bodyOfResponse),
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  private ExceptionEntity createResponseMessage(String message) {
    return new ExceptionEntity(HttpStatus.BAD_REQUEST, message);
  }
}
