package com.github.arturkolaska.reposapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@ConfigurationProperties
@Configuration
public class YAMLConfig {

    private String token;
    private String url;

    public String getToken() {
        return "token " + token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorizationHeader() {
        return token.isEmpty() ? "Null" : "Authorization";
    }

    @Override
    public String toString() {
        return "{token: " + this.getToken() + "}";
    }
}