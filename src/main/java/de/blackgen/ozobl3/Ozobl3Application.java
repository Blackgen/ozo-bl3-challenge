package de.blackgen.ozobl3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Ozobl3Application {

  public static void main(String[] args) {
    SpringApplication.run(Ozobl3Application.class, args);
  }

}