package com.meal.service;

import com.meal.entity.User;

import java.util.List;

public interface UserService {

  Iterable<User> findAll();
  User findOne(int id);
  User findByLogin(String login);
  User createUser(User user);
  User updateUser(User user);
  void deleteUser(int id);

}


