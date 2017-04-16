package com.meal.service.impl;

import java.util.Date;
import java.util.List;
import com.meal.dao.UserDao;
import com.meal.dao.UserDataDao;
import com.meal.entity.UserDataEntity;
import com.meal.entity.UserEntity;
import com.meal.entity.UserFullEntity;
import com.meal.service.UserService;
import com.meal.utils.HelpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserDao userDao;
  private final UserDataDao userDataDao;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  private final Date dateTime = new Date();
  @Autowired
  public UserServiceImpl(UserDao userDao, UserDataDao userDataDao) {
    this.userDao = userDao;
    this.userDataDao = userDataDao;
  }

  public Iterable<UserEntity> findAll() {
    return userDao.findAll();
  }

  public UserEntity findOne(int id) {
    return userDao.findOne(id);
  }

  public UserEntity findByLogin(String login) {
    if(HelpUtils.isNullOrEmpty(login)){
      return null;
    }
    List<UserEntity> userEntities =  userDao.findFirstByLogin(login);
    return userEntities.isEmpty() ? null : userEntities.get(0);
  }

 // public UserEntity createUser(UserEntity user) throws ServiceException {
  public UserEntity createUser(UserEntity user) {
    if(!userIsValid(user)){
      return null;
    }

    String passwordHash = user.getPassword();
    passwordHash = passwordEncoder.encode(passwordHash);

    user.setPassword(passwordHash);
    user.setStage((byte)0);
    user.setRole("USER");
    user.setRegisteredAt(new java.sql.Timestamp(dateTime.getTime()));

    try {
      user = userDao.save(user);
    } catch(Exception e){
     // throw new ServiceException(e);
    }
    user.setPassword("");
    return user;
  }

  public UserEntity updateUser(UserEntity user) {
    return userDao.save(user);
  }

  public void deleteUser(int id) {
    userDao.delete(id);
  }

  public UserDataEntity findUserData(int id) {
    return userDataDao.findOne(id);
  }

  public UserDataEntity findUserDataByUserId(int id) {
    return null; //TODO: userDataDao.findOne(id);
  }

  public UserDataEntity createUserData(UserDataEntity userData) {
    return userDataDao.save(userData);
  }

  public UserDataEntity updateUserData(UserDataEntity userData) {
    return userDataDao.save(userData);
  }

  public void deleteUserData(int id) {
    userDataDao.delete(id);
  }

  public void clearUserData(int id) { }

  public UserFullEntity findUserWithUserData(int userId) {
    return null;
  }

  private boolean userIsValid(UserEntity user){
    if(user == null){
      return false;
    }
    if(HelpUtils.isNullOrEmpty(user.getEmail())){
      return false;
    }
    if(HelpUtils.isNullOrEmpty(user.getName())){
      return false;
    }
    if(HelpUtils.isNullOrEmpty(user.getSurname())){
      return false;
    }
    if(HelpUtils.isNullOrEmpty(user.getLogin())){
      return false;
    }
    if(HelpUtils.isNullOrEmpty(user.getPassword())){
      return false;
    }
    return true;
  }
}