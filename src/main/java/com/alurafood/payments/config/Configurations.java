package com.alurafood.payments.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

  @Bean
  public ModelMapper generateModelMapper() {
    return new ModelMapper();
  }

}
