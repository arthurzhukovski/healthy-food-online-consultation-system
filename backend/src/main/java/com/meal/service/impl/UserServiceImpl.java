package com.meal.service.impl;

import java.util.Date;

import com.meal.dao.UserDataRepository;
import com.meal.dao.UserRepository;
import com.meal.entity.RoleEnum;
import com.meal.entity.UserEntity;
import com.meal.entity.UserDataEntity;
import com.meal.entity.UserFullEntity;
import com.meal.service.Exception.ServiceException;
import com.meal.service.UserService;
import com.meal.utils.HelpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserDataRepository userDataRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final Date dateTime;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserDataRepository userDataRepository) {
    this.userRepository = userRepository;
    this.userDataRepository = userDataRepository;
    this.passwordEncoder = new BCryptPasswordEncoder();
    this.dateTime = new Date();
  }

  public Iterable<UserEntity> findAll() {
    return userRepository.findAll();
  }
  public Iterable<UserEntity> findCoachs(){ return userRepository.findByRole(RoleEnum.COACH); }


  public UserEntity findOne(int id) {
    return userRepository.findOne(id);
  }

  public UserEntity findByLogin(String login) throws ServiceException {
    Assert.notNull(login, "login can't be null");
    if(login.isEmpty()) { throw new IllegalArgumentException("login can't be empty"); }

    UserEntity user =  userRepository.findByLogin(login);

    if(user == null) {
      throw new ServiceException("User with login " + login + " doesn't exists");
    }
    return user;
  }

  public UserEntity createUser(UserEntity user) throws ServiceException {
    validateUser(user);

    String passwordHash = user.getPassword();
    passwordHash = passwordEncoder.encode(passwordHash);

    user.setPassword(passwordHash);
    user.setStage((byte)0);
    user.setRole(RoleEnum.USER);
    user.setRegisteredAt(new java.sql.Timestamp(dateTime.getTime()));

    user = userRepository.save(user);
    return user;
  }

  public UserEntity updateUser(UserEntity user) throws ServiceException {
    Assert.notNull(user);
    UserEntity oldUser = userRepository.findOne(user.getId());
    if(oldUser == null) {
      throw new ServiceException("no user with " + user.getId() + " id");
    }

    user = updateUserFields(oldUser, user);

    return userRepository.save(user);
  }

  public void deleteUser(int id) {
    userRepository.delete(id);
    this.deleteUserDataByUserId(id);
  }

  public UserDataEntity findUserData(int id) {
    return userDataRepository.findOne(id);
  }

  public UserDataEntity updateUserData(UserDataEntity userData) {
    return userDataRepository.save(userData);
  }

  public void deleteUserDataByUserId(int id) {
    userDataRepository.delete(id);
  }

//  public UserFullEntity findUserWithUserData(int userId) {
//    return null;
//  }

  private UserEntity updateUserFields(UserEntity user, UserEntity newUser) throws ServiceException {
    Assert.notNull(user, "user can't be null");
    Assert.notNull(newUser, "new user can't be null");

    if(newUser.getGroupId() != null){
      user.setGroupId(newUser.getGroupId());
    }
    if(newUser.getRole() != null) {
      user.setRole(newUser.getRole());
    }
    if(newUser.getName() != null) {
      if(newUser.getName().isEmpty()) {
        throw new ServiceException("user name can't be null");
      }
      user.setName(newUser.getName());
    }
    if(newUser.getSurname() != null) {
      if(newUser.getSurname().isEmpty()) {
        throw new ServiceException("user name can't be null");
      }
      user.setSurname(newUser.getSurname());
    }
    if(newUser.getUserData() != null) {
      UserDataEntity newUserData = newUser.getUserData();
      UserDataEntity userData = user.getUserData();
      if(newUserData.getWeight() != null){
        userData.setWeight(userData.getWeight());
      }
      if(newUserData.getHeight() != null){
        userData.setHeight(userData.getHeight());
      }
      if(newUserData.getBirthdate() != null){
        userData.setBirthdate(userData.getBirthdate());
      }
      if(newUserData.getGender() != null){
        userData.setGender(userData.getGender());
      }
      user.setUserData(userData);
    }
    return user;
  }
  private UserEntity updateUserDataFields(UserEntity user, UserEntity newUser) throws ServiceException {
    Assert.notNull(user, "user can't be null");
    Assert.notNull(newUser, "new user can't be null");

    if(newUser.getGroupId() != null){
      user.setGroupId(newUser.getGroupId());
    }
    if(newUser.getRole() != null) {
      user.setRole(newUser.getRole());
    }
    if(newUser.getName() != null) {
      if(newUser.getName().isEmpty()) {
        throw new ServiceException("user name can't be null");
      }
      user.setName(newUser.getName());
    }
    if(newUser.getSurname() != null) {
      if(newUser.getSurname().isEmpty()) {
        throw new ServiceException("user name can't be null");
      }
      user.setSurname(newUser.getSurname());
    }

    return user;
  }
  private void validateUser(UserEntity user) throws ServiceException{
    if(user == null){
      throw new ServiceException("user can't be null");
    }
    if(HelpUtils.isNullOrEmpty(user.getEmail())){
      throw new ServiceException("user email is invalid");
    }
    if(HelpUtils.isNullOrEmpty(user.getName())){
      throw new ServiceException("user name is invalid");
    }
    if(HelpUtils.isNullOrEmpty(user.getSurname())){
      throw new ServiceException("user surname is invalid");
    }
    if(HelpUtils.isNullOrEmpty(user.getLogin())){
      throw new ServiceException("user login is invalid");
    }
    if(HelpUtils.isNullOrEmpty(user.getPassword())){
      throw new ServiceException("user password is invalid");
    }
  }
}