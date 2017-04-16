package com.meal.controller;

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
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/")
public class AuthController {

  @Autowired
  private AuthService authService;
 // private final ResourceBundle resourceBundle = ResourceBundle.getBundle("jwt");

//  @Autowired
//  public AuthController(AuthService authService) {
//    this.authService = authService;
//    this.resourceBundle = ResourceBundle.getBundle("jwt");
//  }

  @RequestMapping(value = "/login/{id}", method = RequestMethod.POST)
  public ResponseEntity<LoginResponse> login(@RequestBody LoginEntity loginEntity)
          throws ServletException {

    UserEntity user = authService.login(loginEntity.login, loginEntity.password);

    if (user == null) {
      return new ResponseEntity<LoginResponse>(HttpStatus.UNAUTHORIZED);
    }

    return new ResponseEntity<LoginResponse>( new LoginResponse(
            Jwts.builder()
            .setSubject(String.valueOf(user.getId()))
            //.claim("roles", user)
            //.setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "something-secret-you-cannot-keep-it").compact())
    , HttpStatus.OK);
  }

  @SuppressWarnings("unused")
  private class LoginResponse {
    public String token;

    public LoginResponse(final String token) {
      this.token = token;
    }
  }

  private class LoginEntity {
    public String login;
    public String password;
  }
}


