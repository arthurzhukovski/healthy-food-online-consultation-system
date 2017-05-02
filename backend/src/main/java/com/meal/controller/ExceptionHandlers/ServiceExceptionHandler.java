package com.meal.controller.ExceptionHandlers;

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

  @ExceptionHandler(value = { ServiceException.class })
  protected ResponseEntity<Object> handleServiceConflict(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = ex.getMessage();
    if(bodyOfResponse == null) {
      bodyOfResponse = "BadRequest";
    }
    return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }


}