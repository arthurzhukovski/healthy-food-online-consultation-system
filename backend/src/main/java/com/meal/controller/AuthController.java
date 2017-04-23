package com.meal.controller;

import com.meal.entity.LoginRequest;
import com.meal.entity.TokenResponse;
import com.meal.entity.UserEntity;
import com.meal.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

@CrossOrigin
@RestController
public class AuthController {

  private AuthService authService;
 // private final ResourceBundle resourceBundle = ResourceBundle.getBundle("jwt");

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
//    this.resourceBundle = ResourceBundle.getBundle("jwt");
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseEntity<UserWithToken> login(@RequestBody LoginRequest loginEntity)
          throws ServletException {

    UserEntity user = authService.login(loginEntity.getLogin(), loginEntity.getPassword());
    ///WAT
    if (user == null) {
      return new ResponseEntity<UserWithToken>(HttpStatus.UNAUTHORIZED);
    }

    TokenResponse token = new TokenResponse();
    token.setToken(Jwts.builder()
            .setSubject(String.valueOf(user.getId()))
            //.claim("userId", user.getId())
            //.setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "something-secret-you-cannot-keep-it").compact());
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


