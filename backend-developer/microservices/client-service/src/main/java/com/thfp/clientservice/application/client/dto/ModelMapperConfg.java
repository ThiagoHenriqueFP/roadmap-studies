package com.thfp.clientservice.application.client.dto;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfg {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
