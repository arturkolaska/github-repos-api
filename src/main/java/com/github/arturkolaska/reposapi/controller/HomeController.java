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

import static com.github.arturkolaska.reposapi.constants.StringConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HomeController {

    @Autowired
    private YAMLConfig config;

    @GetMapping(path = {"/", "/v1"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String, String>>> getDefaultMessage() {
        return ResponseEntity.ok(Arrays.asList(
                Collections.singletonMap(USER_REPOS_KEY, config.getUrl() + REPOS_URI),
                Collections.singletonMap(USER_TOTAL_STARS_KEY, config.getUrl() + TOTAL_STARS_URI)));
    }
}