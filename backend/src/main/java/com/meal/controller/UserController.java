package com.meal.controller;

import com.meal.entity.User;
//import com.meal.entity.UserData;
//import com.meal.service.UserDataService;
import com.meal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;
  //private UserDataService userDataService;

  /*
    GET ALL USERS
   */
  @RequestMapping(value="/users", method = RequestMethod.GET)
  public ResponseEntity<Iterable<User>> getUsers() {
    try {
      Iterable<User> users = userService.findAll();
      return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
    } catch(Exception e) { //TODO: exception type (service exception) (глобальный обратчик )
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /*
     CREATE USER
   */
  @RequestMapping(value="/users", method = RequestMethod.POST)
  public ResponseEntity<User> createUser(@RequestBody User user) {
    try {
      User createdUser =  userService.createUser(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.OK);
//      } else {
//        //TODO: throw exception
//        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//      }
    } catch(Exception e) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /*
    UPDATE USER
   */
  @RequestMapping(value="/users/:id", method = RequestMethod.PUT)
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    try {
      User updatedUser = userService.updateUser(user);
      //TODO: throw exception
      return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    } catch(Exception e) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /*
    DELETE USER
   */
  @RequestMapping(value="/users/:id", method = RequestMethod.DELETE)
  public ResponseEntity deleteUser(int id) {//TODO: id param
    try {
      userService.deleteUser(id);
      return new ResponseEntity(HttpStatus.OK);
    } catch(Exception e) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /*
    GET USER
   */
  @RequestMapping(value="/users/:id", method = RequestMethod.GET)
  public ResponseEntity<User> getUser(int id) {
    try {
      User user = userService.findOne(id);
      //TODO: throw exception
      return new ResponseEntity<User>(user, HttpStatus.OK);
    } catch(Exception e) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
//
//  /*
//    GET USER DATA
//   */
//  @RequestMapping(value="/users/:userId/data/:id", method = RequestMethod.GET)
//  public ResponseEntity<UserData> getUserData(int id, int userId) {
//    try {
//      UserData userData = userDataService.getUserData(id, userId);
//      //TODO: throw exception
//      return new ResponseEntity<UserData>(userData, HttpStatus.OK);
//    } catch(Exception e) {
//      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
//
//  //TODO: Create automatically?
//  /*
//    CREATE USER DATA
//   */
//  @RequestMapping(value="/users/:userId/data/:id", method = RequestMethod.POST)
//  public ResponseEntity createUserData(@RequestBody UserData userData, int userId) {
//    try {
//      UserData createUserData = userDataService.createUserData(userData, userId);
//      return new ResponseEntity(createUserData, HttpStatus.OK);
////      } else {
////        //TODO: throw exception
////        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
////      }
//    } catch(Exception e) {
//      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
//
//  /*
//    UPDATE USER
//   */
//  @RequestMapping(value="/users/:userId/data/:id", method = RequestMethod.PUT)
//  public ResponseEntity<UserData> updateUser(@RequestBody UserData userData, int userId) {
//    try {
//      UserData updatedUserData = userDataService.updateUserData(userData, userId);
//      //TODO: throw exception
//      return new ResponseEntity<UserData>(updatedUserData, HttpStatus.OK);
//    } catch(Exception e) {
//      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
//
//  /*
//    DELETE USER DATA
//   */
//  @RequestMapping(value="/users/:userId/data/:id", method = RequestMethod.DELETE)
//  public ResponseEntity deleteUserData(int id, int userId) {//TODO: id param
//    try {
//      userDataService.deleteUserData(id, userId);
//      return new ResponseEntity(HttpStatus.OK);
//    } catch(Exception e) {
//      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
}
