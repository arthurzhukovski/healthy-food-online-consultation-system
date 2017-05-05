package com.meal.service;

import com.meal.entity.UserEntity;
import com.meal.service.Exception.ServiceException;

public interface AuthService {

  UserEntity login(String login, String password) throws ServiceException;
}
