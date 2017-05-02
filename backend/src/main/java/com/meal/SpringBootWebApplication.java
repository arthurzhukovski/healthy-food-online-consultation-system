package com.meal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class SpringBootWebApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(SpringBootWebApplication.class, args);
  }

}

