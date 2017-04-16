package com.meal.service.impl;

import com.meal.entity.UserEntity;
import com.meal.service.AuthService;
import com.meal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private UserService userService;

  @Autowired
  public AuthServiceImpl(UserService userService) {
    this.userService = userService;
  }

  public UserEntity login(String login, String password) {
    UserEntity user = userService.findByLogin(login);
    //String passwordHash = ""; //TODO: add hash util


    return null;
  }

}
