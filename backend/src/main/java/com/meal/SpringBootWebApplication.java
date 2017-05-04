package com.meal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class SpringBootWebApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(SpringBootWebApplication.class, args);
  }

//  @Bean
//  EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
//    return (ConfigurableEmbeddedServletContainer container) -> {
//      if (container instanceof TomcatEmbeddedServletContainerFactory) {
//        TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
//        tomcat.addConnectorCustomizers(
//                (connector) -> {
//                  connector.setMaxPostSize(10000000); // 10 MB
//                }
//        );
//      }
//    };
//  }

}

