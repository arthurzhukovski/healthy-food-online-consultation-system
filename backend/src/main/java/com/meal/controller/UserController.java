package com.meal.controller;

import com.meal.entity.RoleEnum;
import com.meal.entity.UserEntity;
import com.meal.entity.UserDataEntity;
import com.meal.entity.UserFullEntity;
import com.meal.security.Secured;
import com.meal.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/users", method = RequestMethod.GET)
  public ResponseEntity<Iterable<UserEntity>> getUsers() {
    Iterable<UserEntity> users = userService.findAll();
    return new ResponseEntity<Iterable<UserEntity>>(users, HttpStatus.OK);
  }

  /*
     CREATE USER
   */
  @Secured({})
  @RequestMapping(value="/users", method = RequestMethod.POST)
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
    UserEntity createdUser = userService.createUser(user);
    return new ResponseEntity<UserEntity>(createdUser, HttpStatus.OK);
  }

  /*
    UPDATE USER
   */
  @Secured({RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.COACH})
  @RequestMapping(value="/users", method = RequestMethod.PUT)
  public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user,
                                               @RequestAttribute("user") UserEntity currentUser) {
    userService.hasPermission(user, currentUser, RoleEnum.USER);
    userService.hasPermission(user, currentUser, RoleEnum.COACH);
    UserEntity updatedUser = userService.updateUser(user);
    return new ResponseEntity<UserEntity>(updatedUser, HttpStatus.OK);
  }

  /*
    DELETE USER
   */
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/users/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteUser(@PathVariable(value = "id") int id) {
    userService.deleteUser(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  /*
    GET CURRENT USER
   */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH, RoleEnum.USER})
  @RequestMapping(value="/users/current", method = RequestMethod.GET)
  public ResponseEntity<UserEntity> getCurrentUser(@RequestAttribute("user") UserEntity user) {
    return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
  }

  /*
    GET USER
   */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH, RoleEnum.USER})
  @RequestMapping(value="/users/{id}", method = RequestMethod.GET)
  public ResponseEntity<UserEntity> getUser(@PathVariable(value = "id") int id,
                                            @RequestAttribute("user") UserEntity currentUser) {
    userService.hasPermission(id, currentUser, RoleEnum.USER);
    UserEntity user = userService.findOne(id);
    return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
  }

  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/users/coach", method = RequestMethod.GET)
  public ResponseEntity<Iterable<UserEntity>> findCoachs() {
    Iterable<UserEntity> coach = userService.findCoachs();
    return new ResponseEntity<Iterable<UserEntity>>(coach, HttpStatus.OK);
  }

}
