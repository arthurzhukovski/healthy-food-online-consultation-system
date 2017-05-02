package com.meal.service;

import com.meal.entity.RoleEnum;
import com.meal.entity.UserDataEntity;
import com.meal.entity.UserEntity;
import com.meal.service.Exception.ForbiddenException;
import com.meal.service.Exception.ServiceException;

import javax.management.relation.Role;


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
  void hasPermission(int id, UserEntity currentUser, RoleEnum checkRole) throws ForbiddenException;
  void hasPermission(UserEntity user, UserEntity currentUser, RoleEnum checkRole) throws ForbiddenException, ServiceException;


  /*
    UserDataEntity
   */

  UserDataEntity updateUserData(UserDataEntity userData);


}


