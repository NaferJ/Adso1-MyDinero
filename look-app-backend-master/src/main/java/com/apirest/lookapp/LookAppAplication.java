package com.apirest.lookapp;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAdminServer
@SpringBootApplication
public class LookAppAplication {

  public static void main(String[] args) {
    SpringApplication.run(LookAppAplication.class, args);
  }
}
