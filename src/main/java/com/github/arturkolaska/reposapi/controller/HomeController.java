package com.github.arturkolaska.reposapi.controller;

import com.github.arturkolaska.reposapi.config.YAMLConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.github.arturkolaska.reposapi.constants.StringConstants.REPOS_URI;
import static com.github.arturkolaska.reposapi.constants.StringConstants.TOTAL_STARS_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HomeController {

    @Autowired
    private YAMLConfig config;

    @GetMapping(path = {"/", "/v1"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String, String>>> getAllRepositories() {
        return ResponseEntity.ok(Arrays.asList(
                Collections.singletonMap("user_repositories_uri", config.getUrl() + REPOS_URI),
                Collections.singletonMap("user_total_stars_uri", config.getUrl() + TOTAL_STARS_URI)));
    }
}