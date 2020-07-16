package com.everis.msclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;


@SpringBootApplication
public class MsClientApplication {

  public static void main(final String[] args) {
    SpringApplication.run(MsClientApplication.class, args);
  }

}
