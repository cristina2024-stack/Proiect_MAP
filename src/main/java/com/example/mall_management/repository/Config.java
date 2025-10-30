package com.example.mall_management.repository;

import com.example.mall_management.repository.Mall_Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Mall_Repository mallRepository() {
        return new Mall_Repository();
    }
}

