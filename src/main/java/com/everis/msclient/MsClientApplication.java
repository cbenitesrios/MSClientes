package com.everis.msclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.reactive.config.EnableWebFlux; 
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2 
@EnableEurekaClient
public class MsClientApplication {

  public static void main(final String[] args) {
    SpringApplication.run(MsClientApplication.class, args);
  }

}
