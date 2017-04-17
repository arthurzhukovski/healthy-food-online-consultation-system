package com.meal.interceptor;

import com.meal.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@Scope("AuthInterceptor")
public class AuthInterceptor extends HandlerInterceptorAdapter{
  @Autowired
  private AuthService authService;
  //ResourceBundle resource = ResourceBundle.getBundle("jwt");

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
    // if (req.getMethod().equals("OPTIONS")) return true;

    String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      response.sendError(401);
      return false;
    }
    String token = authHeader.substring(7); // The part after "Bearer "

    Claims claims = null;
    try {
      //claims = Jwts.parser().setSigningKey(resource.getString("token.secret"))
      claims = Jwts.parser().setSigningKey("something-secret-you-cannot-keep-it")
              .parseClaimsJws(token).getBody();
      //TODO claims -> user
      request.setAttribute("user", claims);
    } catch (final SignatureException e) {
      response.sendError(401);
      return false;
    }

    return claims != null;
  }
}