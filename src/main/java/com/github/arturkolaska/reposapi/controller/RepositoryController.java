package com.github.arturkolaska.reposapi.controller;

import com.github.arturkolaska.reposapi.model.RepositoryModel;
import com.github.arturkolaska.reposapi.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/v1/{username}/repos")
public class RepositoryController {

    private final static Logger log = LoggerFactory.getLogger(RepositoryController.class);
    @Autowired
    private RepositoryService service;

    @GetMapping
    public ResponseEntity<List<RepositoryModel>> getAllRepositories(@PathVariable String username) {
        log.info("Getting repositories from github.com/{}.", username);
        List<RepositoryModel> repositories = service.getAllRepositoriesByUsername(username);
        log.info(repositories.toString());
        return ResponseEntity.ok(repositories);
    }

    @GetMapping(path = "/total-stars")
    public ResponseEntity<String> getStarsCountSum(@PathVariable String username,
                                                   @RequestParam("format") Optional<String> format) {
        log.info("Getting the sum of stars from github.com/{}.", username);
        var httpHeaders = new HttpHeaders();
        if (format.isPresent() && format.get().equals("json")) {
            httpHeaders.setContentType(new MediaType("application", "json"));
        } else {
            httpHeaders.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));
        }
        return new ResponseEntity<>(String.valueOf(service.getStarsCountSumByUsername(username)), httpHeaders, HttpStatus.OK);
    }
}