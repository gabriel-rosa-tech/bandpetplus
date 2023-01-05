package com.br.bandtec.projetobandpetjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BandpetConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){

            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedMethods("GET","POST","PUT","DELETE")
                        .allowedHeaders("*")
                        .allowedOrigins("http://ec2-3-212-123-208.compute-1.amazonaws.com",
                                "http://localhost:3000");
            }
        };
    }
}
