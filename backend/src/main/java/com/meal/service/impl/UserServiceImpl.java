package com.meal.service.impl;

import java.util.Date;

import com.meal.dao.UserDataRepository;
import com.meal.dao.UserRepository;
import com.meal.entity.RoleEnum;
import com.meal.entity.UserEntity;
import com.meal.entity.UserDataEntity;
import com.meal.entity.UserFullEntity;
import com.meal.service.UserService;
import com.meal.utils.HelpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

  public UserEntity findOne(int id) {
    return userRepository.findOne(id);
  }

  public UserEntity findByLogin(String login) {
    if(HelpUtils.isNullOrEmpty(login)){
      return null;
    }

    UserEntity user =  userRepository.findByLogin(login);
    return user;
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
    user.setRole(RoleEnum.USER);
    user.setRegisteredAt(new java.sql.Timestamp(dateTime.getTime()));

    try {
      user = userRepository.save(user);
    } catch(Exception e){
     // throw new ServiceException(e);
    }
    return user;
  }

  public UserEntity updateUser(UserEntity user) {
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
    userDataRepository.delete(id);//TODO:
  }

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