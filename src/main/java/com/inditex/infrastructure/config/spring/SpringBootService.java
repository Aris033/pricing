package com.inditex.infrastructure.config.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.inditex.infrastructure")
@EntityScan(basePackages = "com.inditex.domain")
public class SpringBootService {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootService.class, args);
  }

}