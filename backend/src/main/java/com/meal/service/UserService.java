package com.meal.service;

import com.meal.entity.UserDataEntity;
import com.meal.entity.UserEntity;
import com.meal.entity.UserFullEntity;

public interface UserService {

  /*
    UserDataEntity
   */

  Iterable<UserEntity> findAll();
  UserEntity findOne(int id);
  UserEntity findByLogin(String login);
  UserEntity createUser(UserEntity user);// throws ServiceException;
  UserEntity updateUser(UserEntity user);
  void deleteUser(int id);

  /*
    UserDataEntity
   */

  UserDataEntity findUserData(int id);
  UserDataEntity findUserDataByUserId(int id);
  UserDataEntity createUserData(UserDataEntity userData);
  UserDataEntity updateUserData(UserDataEntity userData);
  void deleteUserData(int id);
  void clearUserData(int id);

  /*
    UserEntity with UserDataEntity
   */
  UserFullEntity findUserWithUserData(int userId);

}


