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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

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
  public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginEntity)
          throws ServletException {

    UserEntity user = authService.login(loginEntity.getLogin(), loginEntity.getPassword());

    if (user == null) {
      return new ResponseEntity<TokenResponse>(HttpStatus.UNAUTHORIZED);
    }

    TokenResponse token = new TokenResponse();
    token.setToken(Jwts.builder()
            .setSubject(String.valueOf(user.getId()))
            //.claim("roles", user)
            //.setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "something-secret-you-cannot-keep-it").compact());
    return new ResponseEntity<TokenResponse>(token, HttpStatus.OK);
  }


}


