package com.meal.service.Exception;

public class ForbiddenException extends ServiceException {

  private String message;

  public ForbiddenException(String msg) {
    super(msg);
    message = msg;
  }
  public ForbiddenException(String msg, Exception ex) {
    super(msg, ex);
    message = msg;
  }
  public ForbiddenException(Exception ex) {
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