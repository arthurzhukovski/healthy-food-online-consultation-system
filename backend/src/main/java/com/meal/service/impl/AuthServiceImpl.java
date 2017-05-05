package com.meal.service.impl;

import com.meal.entity.UserEntity;
import com.meal.service.AuthService;
import com.meal.service.Exception.ServiceException;
import com.meal.service.UserService;
import com.meal.utils.HelpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private UserService userService;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public AuthServiceImpl(UserService userService) {
    this.userService = userService;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public UserEntity login(String login, String password) throws ServiceException {
    if(HelpUtils.isNullOrEmpty(login) || HelpUtils.isNullOrEmpty(password)) {
      throw new ServiceException("invalid login or password");
    }
    UserEntity user = userService.findByLogin(login);
    if(user != null){
      if(passwordEncoder.matches(password, user.getPassword())){
        return user;
      }
    }

    throw new ServiceException("invalid login or password");
  }

}
