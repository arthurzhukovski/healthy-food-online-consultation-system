package com.meal.security;

import com.meal.entity.RoleEnum;
import com.meal.entity.UserEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class SecureBeforeMethod {

  @Around("@annotation(com.meal.security.Secured)")
  public ResponseEntity doSecure(ProceedingJoinPoint joinPoint) {
    MethodSignature  signature = (MethodSignature)joinPoint.getSignature();
    Method method = signature.getMethod();
    RoleEnum[] validRoles = method.getAnnotation(Secured.class).value();
   // HttpServletRequest request = getRequestArgument(joinPoint);
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//    if(request != null) {
//      UserEntity currentUser = ((UserEntity) (request).getAttribute("user"));
//      if (currentUser != null) {
//        RoleEnum currentUserLevel = currentUser.getRole();
//        if (validateRole(validRoles, currentUser.getRole())) {
          try {
            return (ResponseEntity) joinPoint.proceed();
          } catch (Throwable exc) {
          }
//        }
//      }
//    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }


  private boolean validateRole(RoleEnum[] roles,RoleEnum role) {
    for (RoleEnum validRole: roles) {
      if (validRole == role) {
        return true;
      }
    }
    return false;
  }

}
