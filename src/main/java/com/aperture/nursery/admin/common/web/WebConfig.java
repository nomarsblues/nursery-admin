package com.aperture.nursery.admin.common.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig {
    @Configuration
    public class GlobalCorsConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurerAdapter() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedOrigins("*")
                            .allowedMethods("PUT", "DELETE","GET","POST")
                            .allowedHeaders("*")
                            .exposedHeaders("access-control-allow-headers",
                                    "access-control-allow-methods",
                                    "access-control-allow-origin",
                                    "access-control-max-age",
                                    "X-Frame-Options")
                            .allowCredentials(false).maxAge(3600);
                }
            };
        }
    }
}
