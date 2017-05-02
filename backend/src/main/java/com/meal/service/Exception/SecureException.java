package com.meal.service.Exception;

public class SecureException extends RuntimeException {

  private String message;

  public SecureException(String msg) {
    super(msg);
    message = msg;
  }
  public SecureException(String msg, Exception ex) {
    super(msg, ex);
    message = msg;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return message;
  }

}