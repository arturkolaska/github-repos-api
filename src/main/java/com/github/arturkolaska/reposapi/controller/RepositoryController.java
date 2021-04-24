package com.github.arturkolaska.reposapi.controller;

import com.github.arturkolaska.reposapi.model.RepositoryModel;
import com.github.arturkolaska.reposapi.service.RepositoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.github.arturkolaska.reposapi.constants.StringConstants.TOTAL_STARS_KEY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/{username}/repos")
public class RepositoryController {

    @Autowired
    private RepositoryServiceImpl service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RepositoryModel>> getAllRepositories(@PathVariable String username) {
        return ResponseEntity
                .ok(service.getAllRepositoriesByUsername(username));
    }

    @GetMapping(path = "/total-stars", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Integer>> getStarsCountSum(@PathVariable String username) {
        return ResponseEntity
                .ok(Collections.singletonMap(TOTAL_STARS_KEY, service.getTotalStarsByUsername(username)));
    }
}