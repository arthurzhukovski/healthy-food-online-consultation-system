package com.meal.service.Exception;

public class ServiceException extends RuntimeException {

  private String message;

  public ServiceException(String msg) {
    super(msg);
    message = msg;
  }
  public ServiceException(String msg, Exception ex) {
    super(msg, ex);
    message = msg;
  }

  public ServiceException(Exception ex) {
    super(ex);
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
