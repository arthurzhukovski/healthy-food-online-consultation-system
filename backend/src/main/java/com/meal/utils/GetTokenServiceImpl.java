package com.meal.utils;

import com.meal.entity.UserEntity;
import com.meal.service.Exception.ServiceException;
import com.meal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.ws.Service;
import java.util.*;

public class GetTokenServiceImpl {

  @Autowired
  private UserService userService;
  //ResourceBundle resource = ResourceBundle.getBundle("jwt");
  //@Override
  public String getToken(String login, String password) throws Exception {
    if (login == null || login.isEmpty() || password == null || password.isEmpty()){
      throw new ServiceException("invalid login or password");
    }

    UserEntity user = userService.findByLogin(login);
    if(user == null) {
      new ServiceException("no such user");
    }
    Map<String, Object> tokenData = new HashMap<String, Object>();
    if (password.equals(user.getPassword())) {

      tokenData.put("clientType", "user");
      tokenData.put("userId", String.valueOf(user.getId()));
      // tokenData.put("username", authorizedUser.getUsername());
      tokenData.put("token_create_date", new Date().getTime());
      tokenData.put("token_expiration_date", 24);

      JwtBuilder jwtBuilder = Jwts.builder();
      jwtBuilder.setExpiration(new Date(24));
      jwtBuilder.setClaims(tokenData);

      String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, "secret key").compact();

      return token;
    } else {
      throw new Exception("Authentication error");
    }
  }
}
