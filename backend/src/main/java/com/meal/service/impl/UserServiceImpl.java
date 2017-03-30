package com.meal.service.impl;

import java.util.List;

import com.meal.dao.UserDao;
import com.meal.entity.User;
import com.meal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public Iterable<User> findAll() {
    return userDao.findAll();
  }

  @Override
  public User findOne(int id) {
    return userDao.findOne(id);
  }

  @Override
  public User findByLogin(String login) {
    return userDao.findByLogin(login);
  }

  @Override
  public User createUser(User user) {
    return userDao.save(user);
  }

  @Override
  public User updateUser(User user) {
    return userDao.save(user);
  }

  @Override
  public void deleteUser(int id) {
    userDao.delete(id);
  }

}