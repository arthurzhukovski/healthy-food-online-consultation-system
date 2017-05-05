package com.meal.entity;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionEntity {

  private final HttpStatus status;
  private final String message;
  private final Date timestamp;

  public ExceptionEntity(final HttpStatus status, String message){
    this.status = status;
    this.message = message;
    this.timestamp = new java.util.Date();
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public Date getTimestamp() {
    return timestamp;
  }
}
