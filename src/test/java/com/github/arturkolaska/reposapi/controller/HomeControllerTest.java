package com.github.arturkolaska.reposapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.github.arturkolaska.reposapi.constants.StringConstants.USER_REPOS_KEY;
import static com.github.arturkolaska.reposapi.constants.StringConstants.USER_TOTAL_STARS_KEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private HomeController controller;
    @Autowired
    private TestRestTemplate client;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void homeUrl_ShouldReturnListOfTwoMapsWithProperKeys() {
        // given
        String url = "http://localhost:" + port + "/";
        HttpMethod method = HttpMethod.GET;

        // when
        ResponseEntity<List<Map<String, String>>> response =
                client.exchange(url, method, null, new ParameterizedTypeReference<>() {
                });

        // then
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        assertTrue(Objects.requireNonNull(response.getBody()).get(0).containsKey(USER_REPOS_KEY));
        assertTrue(response.getBody().get(1).containsKey(USER_TOTAL_STARS_KEY));
    }
}