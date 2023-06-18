package com.mx.configuration;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mx.util.NamingStrategy;

@Configuration
public class JpaConfig {
    @Bean
    public PhysicalNamingStrategy physicalNamingStrategy(){
        return new NamingStrategy();
    }
}
