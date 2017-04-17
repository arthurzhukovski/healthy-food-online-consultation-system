package com.meal.security;

import com.meal.entity.UserEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class SecureBeforeMethod {

  @Around("@annotation(com.meal.security.Secured)")
  public ResponseEntity doSecure(ProceedingJoinPoint joinPoint) {
    MethodSignature  signature = (MethodSignature)joinPoint.getSignature();
    Method method = signature.getMethod();
    int methodLevel = method.getAnnotation(Secured.class).value().getValue();
    UserEntity currentUser = ((UserEntity)((HttpServletRequest)joinPoint.getArgs()[0]).getAttribute("user"));
    if(currentUser != null) {
      //int currentUserLevel = currentUser.getRole().getLevel();
//      if (currentUserLevel >= needLevel) {
        try {
          return (ResponseEntity) joinPoint.proceed();
        } catch (Throwable exc) {}
     // }
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }
}
