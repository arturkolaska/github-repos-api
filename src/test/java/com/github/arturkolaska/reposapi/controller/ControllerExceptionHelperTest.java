package com.github.arturkolaska.reposapi.controller;

import com.github.arturkolaska.reposapi.config.YAMLConfig;
import com.github.arturkolaska.reposapi.model.ErrorContainer;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerExceptionHelperTest {

    private String url;
    private HttpMethod method;
    private String username;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate client;
    @Autowired
    private YAMLConfig config;

    @BeforeEach
    void setUp() {
        url = config.getUrl() + "/v1/";
    }

    @Test
    void handleWebClientErrorException_ShouldReturnRightStatus() {
        // given wrong username
        username = RandomString.make(20);
        url = url.concat(username).concat("/repos");
        method = HttpMethod.GET;

        // when forced exception
        ResponseEntity<ErrorContainer> response = client
                .exchange(url, method, null, new ParameterizedTypeReference<>() {
                });

        // then
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void handleNoHandlerFoundException_ShouldReturnRightStatus() {
        // given wrong endpoint
        url = url.concat("/non-existing_resource");
        method = HttpMethod.GET;

        // when forced exception
        ResponseEntity<ErrorContainer> response = client
                .exchange(url, method, null, new ParameterizedTypeReference<>() {
                });

        // then
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void handleHttpRequestMethodNotSupportedException_ShouldReturnRightStatus() {
        // given wrong http method
        username = "arturkolaska";
        url = url.concat(username).concat("/repos");
        method = HttpMethod.POST;

        // when forced exception
        ResponseEntity<ErrorContainer> response = client
                .exchange(url, method, null, new ParameterizedTypeReference<>() {
                });

        // then
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    }
}