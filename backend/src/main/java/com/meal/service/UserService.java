package com.meal.service;

import com.meal.entity.UserDataEntity;
import com.meal.entity.UserEntity;
import com.meal.service.Exception.ServiceException;

public interface UserService {

  /*
    UserDataEntity
   */

  Iterable<UserEntity> findAll();
  Iterable<UserEntity> findCoachs();
  UserEntity findOne(int id) throws ServiceException;
  UserEntity findByLogin(String login) throws ServiceException;
  UserEntity createUser(UserEntity user) throws ServiceException;
  UserEntity updateUser(UserEntity user) throws ServiceException;
  void deleteUser(int id) throws ServiceException;

  /*
    UserDataEntity
   */

  UserDataEntity updateUserData(UserDataEntity userData);


}


