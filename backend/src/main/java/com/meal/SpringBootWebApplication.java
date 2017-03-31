package com.meal;

import com.meal.dao.UserDao;
import com.meal.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ComponentScan
public class SpringBootWebApplication extends SpringBootServletInitializer {

//  @Bean
//  public FilterRegistrationBean jwtFilter() {
//    final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//    registrationBean.setFilter(new JwtFilter());
//    registrationBean.addUrlPatterns("/users/*");
//
//    return registrationBean;
//  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SpringBootWebApplication.class);
  }

  public static void main(String[] args) throws Exception {

    ClassPathXmlApplicationContext context = new
            ClassPathXmlApplicationContext("spring.xml");

    UserDao userDao = context.getBean(UserDao.class);

    SpringApplication.run(SpringBootWebApplication.class, args);
  }

}