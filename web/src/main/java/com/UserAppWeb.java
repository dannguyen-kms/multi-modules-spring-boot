package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class UserAppWeb extends SpringBootServletInitializer {
  /** Make application context and run it. */
  public static void main(final String[] args) {
    SpringApplication.run(UserAppWeb.class, args);
  }
}
