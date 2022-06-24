/**
 * An project for learning spring boot.
 *
 * @author hieudan
 * @version 1.0
 */
package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** Main Project to Learn Spring boot. */
@EnableJpaRepositories
@SpringBootApplication
public class Main extends SpringBootServletInitializer {
  /** Make application context and run it. */
  public static void main(final String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
