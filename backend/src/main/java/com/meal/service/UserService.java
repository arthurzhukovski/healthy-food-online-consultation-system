package com.meal.service;

import com.meal.entity.RoleEnum;
import com.meal.entity.UserDataEntity;
import com.meal.entity.UserEntity;
import com.meal.entity.UserView;
import com.meal.service.Exception.ForbiddenException;
import com.meal.service.Exception.ServiceException;
import com.meal.service.impl.model.entity.ViewerFactoryInterface;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


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
  String getRaiting(int id);
  String getReportsPerDay(int id);
  String GoodMarksCount(int id);
  String BadMarksCount(int id);
  ///
  String getMarksCount(int id);
  String GoodMark(int id);
  String getGroupsCount(int id);
  String getUsersCount(int id);

  void createDoc(String type,
                 HttpServletResponse response,
                 List<UserView> entities,
                 ViewerFactoryInterface viewerFactory,
                 boolean isEncrypt);
  void createCoachDoc(String type,
                 HttpServletResponse response,
                 List<UserView> entities,
                 ViewerFactoryInterface viewerFactory,
                 boolean isEncrypt);
  /*
    UserDataEntity
   */

  UserDataEntity updateUserData(UserDataEntity userData);


}


