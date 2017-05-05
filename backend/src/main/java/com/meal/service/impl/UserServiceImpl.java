package com.meal.service.impl;

import java.util.Date;

import com.meal.dao.UserDataRepository;
import com.meal.dao.UserRepository;
import com.meal.entity.RoleEnum;
import com.meal.entity.UserEntity;
import com.meal.entity.UserDataEntity;
import com.meal.service.Exception.ForbiddenException;
import com.meal.service.Exception.ServiceException;
import com.meal.service.UserService;
import com.meal.utils.HelpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

  public void hasPermission(int id, UserEntity currentUser, RoleEnum checkRole) throws ForbiddenException {
    if(currentUser == null || currentUser.getRole() == null) {
        throw new ForbiddenException("Forbidden");
    } else {
      if(currentUser.getRole() == checkRole && currentUser.getId() != id){
        throw new ForbiddenException("Forbidden");
      }
    }
  }
  public void hasPermission(UserEntity user, UserEntity currentUser, RoleEnum checkRole) throws ForbiddenException,
          ServiceException {
    Assert.notNull(user);
    if(currentUser == null || currentUser.getRole() == null) {
      throw new ForbiddenException("Forbidden");
    } else {
      if(currentUser.getRole() == checkRole && currentUser.getId() != user.getId()){
        throw new ForbiddenException("Forbidden");
      }
    }
  }

  public Iterable<UserEntity> findAll() {
    return userRepository.findAll();
  }
  public Iterable<UserEntity> findCoachs(){ return userRepository.findByRole(RoleEnum.COACH); }

  @Transactional
  public UserEntity findOne(int id) {
    return userRepository.findOne(id);
  }

  @Transactional
  public UserEntity findByLogin(String login) throws ServiceException {
    Assert.notNull(login, "login can't be null");
    if(login.isEmpty()) { throw new IllegalArgumentException("login can't be empty"); }

    UserEntity user =  userRepository.findByLogin(login);

    if(user == null) {
      throw new ServiceException("User with login " + login + " doesn't exists");
    }
    return user;
  }

  @Transactional
  public UserEntity createUser(UserEntity user) throws ServiceException {
    validateUser(user);

    String passwordHash = user.getPassword();
    passwordHash = passwordEncoder.encode(passwordHash);

    user.setPassword(passwordHash);
    user.setStage((byte)0);
    user.setRole(RoleEnum.USER);
    user.setRegisteredAt(new java.sql.Timestamp(dateTime.getTime()));
    UserDataEntity userData = new UserDataEntity();
    user.setUserData(userData);
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

  @Transactional
  public void deleteUser(int id) {
    UserEntity user = userRepository.findOne(id);
    if(user != null && user.getUserData() != null) {
      userDataRepository.delete(user.getUserData().getId());
    }
    try {
      userRepository.delete(id);
    } catch (Throwable e) {
      throw new ServiceException("Bad Request");
    }

  }

  @Transactional
  public UserDataEntity updateUserData(UserDataEntity userData) {
    return userDataRepository.save(userData);
  }

  private UserEntity updateUserFields(UserEntity user, UserEntity newUser) throws ServiceException {
    Assert.notNull(user, "user can't be null");
    Assert.notNull(newUser, "new user can't be null");

    if(newUser.getEmail() != null && !newUser.getEmail().equals(user.getEmail())) {
      UserEntity  a = userRepository.findByEmail(newUser.getEmail());
      if(a != null){
        throw new ServiceException("user with such email already exists");
      }
      user.setEmail(newUser.getEmail());
    }
    if(newUser.getLogin() != null && !newUser.getLogin().equals(user.getLogin())) {
      if(userRepository.findByLogin(newUser.getLogin()) != null){
        throw new ServiceException("user with such login already exists");
      }
      user.setLogin(newUser.getLogin());
    }
    if(newUser.getPassword() != null){
      String passwordHash = newUser.getPassword();
      passwordHash = passwordEncoder.encode(passwordHash);
      user.setPassword(passwordHash);
    }
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
        userData.setWeight(newUserData.getWeight());
      }
      if(newUserData.getHeight() != null){
        userData.setHeight(newUserData.getHeight());
      }
      if(newUserData.getBirthdate() != null){
        userData.setBirthdate(newUserData.getBirthdate());
      }
      if(newUserData.getGender() != null){
        userData.setGender(newUserData.getGender());
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
  @Transactional
  private void validateUser(UserEntity user) throws ServiceException{
    if(user == null){
      throw new ServiceException("user can't be null");
    }
    if(HelpUtils.isNullOrEmpty(user.getEmail())){
      throw new ServiceException("user email is invalid");
    }
    if(userRepository.findByEmail(user.getEmail()) != null){
      throw new ServiceException("user with such email already exists");
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
    if(userRepository.findByLogin(user.getLogin()) != null){
      throw new ServiceException("user with such login already exists");
    }
    if(HelpUtils.isNullOrEmpty(user.getPassword())){
      throw new ServiceException("user password is invalid");
    }
  }
}