package com.github.arturkolaska.reposapi.service;

import com.github.arturkolaska.reposapi.config.YAMLConfig;
import com.github.arturkolaska.reposapi.model.RepositoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.github.arturkolaska.reposapi.constants.StringConstants.*;

@Service
public class RepositoryService {

    private final static Logger log = LoggerFactory.getLogger(RepositoryService.class);
    private final WebClient client;
    @Autowired
    private YAMLConfig config;

    public RepositoryService() {
        this.client = WebClient.create(GITHUB_API_BASE_URI);
    }

    public List<RepositoryModel> getAllRepositoriesByUsername(String username) {
        log.info("Getting all repositories of user " + username + " from the public GitHub API.");

        return client.get()
                .uri(GITHUB_API_REPOS_URI, username)
                .retrieve()
                .bodyToFlux(RepositoryModel.class)
                .collectList()
                .block();
    }

    public int getStarsCountSumByUsername(String username) {
        log.info("token: " + config.getToken());

        List<RepositoryModel> repositories = getAllRepositoriesByUsername(username);
        int x = repositories.stream().mapToInt(repository -> repository.starsCount).sum();
        return x;
    }
}
