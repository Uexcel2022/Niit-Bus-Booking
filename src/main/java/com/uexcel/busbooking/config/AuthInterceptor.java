package com.uexcel.busbooking.config;

import com.uexcel.busbooking.auth.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthInterceptor implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor()).excludePathPatterns("/**/auth/login", "/**/auth/register");
//        registry.addInterceptor(new TokenInterceptor()).excludePathPatterns("/**/**");
    }
}
