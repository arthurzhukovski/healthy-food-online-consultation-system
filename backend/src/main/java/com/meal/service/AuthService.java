package com.meal.service;

import com.meal.entity.UserEntity;

public interface AuthService {

  UserEntity login(String login, String password);
}
