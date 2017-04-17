package com.meal.controller;

import com.meal.entity.UserEntity;
import com.meal.entity.UserDataEntity;
import com.meal.entity.UserFullEntity;
import com.meal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /*
     GET ALL USERS
   */
  @RequestMapping(value="/users", method = RequestMethod.GET)
  public ResponseEntity<Iterable<UserEntity>> getUsers() {
    Iterable<UserEntity> users = userService.findAll();
    return new ResponseEntity<Iterable<UserEntity>>(users, HttpStatus.OK);
  }

  /*
     CREATE USER
   */
  @RequestMapping(value="/users", method = RequestMethod.POST)
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
    UserEntity createdUser = userService.createUser(user);
    return new ResponseEntity<UserEntity>(createdUser, HttpStatus.OK);
  }

  /*
    UPDATE USER
   */
  @RequestMapping(value="/users", method = RequestMethod.PUT)
  public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
    UserEntity updatedUser = userService.updateUser(user);
    return new ResponseEntity<UserEntity>(updatedUser, HttpStatus.OK);
  }

  /*
    DELETE USER
   */
  @RequestMapping(value="/users", method = RequestMethod.DELETE)
  public ResponseEntity deleteUser(int id) {//TODO: id param
    userService.deleteUser(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  /*
    GET USER
   */
  @RequestMapping(value="/users/{id}", method = RequestMethod.GET)
  public ResponseEntity<UserEntity> getUser(@RequestParam int id) {
    UserEntity user = userService.findOne(id);
    return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
  }

  /*
    UPDATE USER DATA
   */
  @RequestMapping(value="/users/{id}/data", method = RequestMethod.PUT)
  public ResponseEntity<UserDataEntity> updateUser(@RequestBody UserDataEntity userData) {
    UserDataEntity updatedUserData = userService.updateUserData(userData);
    return new ResponseEntity<UserDataEntity>(updatedUserData, HttpStatus.OK);
  }

  /*
    GET USER WITH USER DATA
   */
  @RequestMapping(value="/users/{id}/data", method = RequestMethod.GET)
  public ResponseEntity<UserFullEntity> updateUser(@RequestParam int id) {
    UserFullEntity userFullEntity = userService.findUserWithUserData(id);
    return new ResponseEntity<UserFullEntity>(userFullEntity, HttpStatus.OK);
  }

}
