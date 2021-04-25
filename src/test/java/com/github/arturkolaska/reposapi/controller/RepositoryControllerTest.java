package com.github.arturkolaska.reposapi.controller;

import com.github.arturkolaska.reposapi.model.RepositoryModel;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.github.arturkolaska.reposapi.constants.StringConstants.TOTAL_STARS_KEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RepositoryControllerTest {

    private String url;
    private HttpMethod method;
    private String username;

    @LocalServerPort
    private int port;

    @Autowired
    private RepositoryController controller;
    @Autowired
    private TestRestTemplate client;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:" + port + "/v1/%s/repos";
        method = HttpMethod.GET;
    }

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllRepositories_GivenRightUsername() {
        // given
        username = "arturkolaska";
        url = String.format(url, username);

        // when
        ResponseEntity<List<RepositoryModel>> response = client
                .exchange(url, method, null, new ParameterizedTypeReference<>() {
                });

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertTrue(Objects.requireNonNull(response.getBody()).size() > 0);
    }

    @Test
    public void getAllRepositories_GivenWrongUsername_ShouldReturn404() {
        // given
        username = RandomString.make(20);
        url = String.format(url, username);

        // when
        HttpStatus statusCode = client
                .exchange(url, method, null, new ParameterizedTypeReference<>() {
                }).getStatusCode();

        // then
        assertEquals(HttpStatus.NOT_FOUND, statusCode);
    }

    @Test
    public void getTotalStars_GivenRightUsername() {
        // given
        username = "arturkolaska";
        url = String.format(url, username).concat("/total-stars");

        // when
        ResponseEntity<Map<String, Integer>> response = client
                .exchange(url, method, null, new ParameterizedTypeReference<>() {
                });

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertTrue(Objects.requireNonNull(response.getBody()).containsKey(TOTAL_STARS_KEY));
    }

    @Test
    public void getTotalStars_GivenWrongUsername_ShouldReturn404() {
        // given
        username = RandomString.make(20);
        url = String.format(url, username).concat("/total-stars");

        // when
        HttpStatus statusCode = client
                .exchange(url, method, null, new ParameterizedTypeReference<>() {
                }).getStatusCode();

        // then
        assertEquals(HttpStatus.NOT_FOUND, statusCode);
    }
}