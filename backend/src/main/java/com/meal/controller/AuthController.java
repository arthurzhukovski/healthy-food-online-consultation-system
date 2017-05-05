package com.meal.controller;

import com.meal.entity.LoginRequest;
import com.meal.entity.TokenResponse;
import com.meal.entity.UserEntity;
import com.meal.service.AuthService;
import com.meal.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;


@CrossOrigin
@RestController
public class AuthController {

  private AuthService authService;
  private UserService userService;
  @Value("${jwt.secret}")
  private String SECRET;

  @Autowired
  public AuthController(AuthService authService, UserService userService) {
    this.authService = authService;
    this.userService = userService;
  }

  @RequestMapping(value="/register", method = RequestMethod.POST)
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
    UserEntity createdUser = userService.createUser(user);
    return new ResponseEntity<UserEntity>(createdUser, HttpStatus.OK);
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseEntity<UserWithToken> login(@RequestBody LoginRequest loginEntity){

    UserEntity user = authService.login(loginEntity.getLogin(), loginEntity.getPassword());
    if (user == null) {
      return new ResponseEntity<UserWithToken>(HttpStatus.UNAUTHORIZED);
    }

    TokenResponse token = new TokenResponse();
    token.setToken(Jwts.builder()
            .setSubject(String.valueOf(user.getId()))
            //.claim("userId", user.getId())
            //.setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, SECRET).compact());
    return new ResponseEntity<UserWithToken>(new UserWithToken(user, token), HttpStatus.OK);
  }

  private class UserWithToken {
    private UserEntity user;
    private TokenResponse token;

    public UserWithToken(UserEntity user, TokenResponse token){
      this.user = user;
      this.token = token;
    }

    public UserWithToken(){}

    public UserEntity getUser() {
      return user;
    }

    public void setUser(UserEntity user) {
      this.user = user;
    }

    public TokenResponse getToken() {
      return token;
    }

    public void setToken(TokenResponse token) {
      this.token = token;
    }
  }


}


