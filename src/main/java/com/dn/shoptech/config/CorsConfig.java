package com.dn.shoptech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigure(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT","DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .allowedOriginPatterns("*")
                        .allowedOrigins("http://127.0.0.1:5500/","http://localhost:3000/")
                    ;
            }
        };

    }


}
