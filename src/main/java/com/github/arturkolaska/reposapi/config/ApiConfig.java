package com.github.arturkolaska.reposapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ApiConfig implements WebMvcConfigurer {
/*
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.favorParameter(true)
                .ignoreAcceptHeader(true)
                .parameterName("format")
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("text", MediaType.TEXT_PLAIN);
    }*/
}