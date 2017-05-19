package com.meal.entity;

import com.meal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Transient;

public class UserView {

  UserService service;
  UserEntity user;

  public UserView(UserEntity user, UserService service) {
    this.user = user;
    this.service = service;
  }

  public String getLogin() {
    return String.valueOf(user.getLogin());
  }

  public String getRaiting() {
    return String.valueOf(service.getRaiting(user.getId()));
  }

  public String getReportsPerDay() {
    return String.valueOf(service.getReportsPerDay(user.getId()));
  }

  public String GoodMarksCount() {
    return String.valueOf(service.GoodMarksCount(user.getId()));
  }

  public String BadMarksCount() {
    return String.valueOf(service.BadMarksCount(user.getId()));
  }

  //////////////////////////////////////////
  public String getMarksCount() {
    return String.valueOf(service.getMarksCount(user.getId()));
  }
  public String GoodMark() {
    return String.valueOf(service.GoodMark(user.getId()));
  }
  public String getGroupsCount() {
    return String.valueOf(service.getGroupsCount(user.getId()));
  }

  public String getUsersCount() {
    return String.valueOf(service.getUsersCount(user.getId()));
  }



}
