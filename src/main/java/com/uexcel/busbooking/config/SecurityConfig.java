package com.uexcel.busbooking.config;

import com.uexcel.busbooking.utils.Validation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public Validation validate() {
        return new Validation();
    }

}
