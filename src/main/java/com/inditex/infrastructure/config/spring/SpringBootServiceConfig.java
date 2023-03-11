package com.inditex.infrastructure.config.spring;


import com.inditex.application.repository.PricingRepository;
import com.inditex.application.service.PricingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootServiceConfig {


  @Bean
  public PricingService pricingService(PricingRepository pricingRepository) {
    return new PricingService(pricingRepository);
  }

}