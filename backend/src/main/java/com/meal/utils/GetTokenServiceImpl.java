package com.meal.utils;

import com.meal.entity.UserEntity;
import com.meal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.util.*;

public class GetTokenServiceImpl {

  @Autowired
  private UserService userService;
  ResourceBundle resource = ResourceBundle.getBundle("jwt");
  //@Override
  public String getToken(String login, String password) throws Exception {
    if (login == null || password == null)
      return null;

    UserEntity user = userService.findByLogin(login);
    Map<String, Object> tokenData = new HashMap<String, Object>();
    if (password.equals(user.getPassword())) {

      tokenData.put("clientType", "user");
      tokenData.put("userId", String.valueOf(user.getId()));
      // tokenData.put("username", authorizedUser.getUsername());
      tokenData.put("token_create_date", new Date().getTime());
      tokenData.put("token_expiration_date", resource.getString("expire.hours"));

      JwtBuilder jwtBuilder = Jwts.builder();
      jwtBuilder.setExpiration(new Date(resource.getString("expire.hours")));
      jwtBuilder.setClaims(tokenData);

      String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, resource.getString("token.secret")).compact();

      return token;
    } else {
      throw new Exception("Authentication error");
    }
  }
}
