package com.meal.config;

import com.meal.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

  @Bean
  public AuthInterceptor createAuthInterceptor() {
    return new AuthInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(createAuthInterceptor()).excludePathPatterns("/login",
            "/articles/{pageSize}/{page}","/register","/report/image/{id}");
  }

//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry.addMapping("/**")
//            .allowedOrigins("http://localhost:8080")
//            .allowedMethods("PUT", "DELETE", "HEAD", "OPTIONS", "GET", "POST");
//  }

}