package com.github.arturkolaska.reposapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HomeController {

    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String, String>>> getAllRepositories() {
        return ResponseEntity.ok(Arrays.asList(
                Collections.singletonMap("user_repositories_uri", "/{username}/repos"),
                Collections.singletonMap("user_total_stars_uri", "/{username}/repos/total-stars")));
    }
}