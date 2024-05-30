package com.uexcel.busbooking.config;

import com.uexcel.busbooking.utils.Repos;
import com.uexcel.busbooking.utils.UtilsToken;
import com.uexcel.busbooking.utils.Validation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public Validation validate() {
        return new Validation();
    }

     @Bean
    public Repos geRepository() {
        return new Repos();
    }

    @Bean
    public UtilsToken utilToken() {
        return new UtilsToken();
    }

}
