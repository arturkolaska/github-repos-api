package com.github.arturkolaska.reposapi.service;

import com.github.arturkolaska.reposapi.config.YAMLConfig;
import com.github.arturkolaska.reposapi.exception.WebClientErrorException;
import com.github.arturkolaska.reposapi.model.RepositoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.TimeoutException;

import static com.github.arturkolaska.reposapi.constants.NumericConstants.CONN_TIMEOUT;
import static com.github.arturkolaska.reposapi.constants.StringConstants.GITHUB_API_BASE_URI;
import static com.github.arturkolaska.reposapi.constants.StringConstants.GITHUB_API_REPOS_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class RepositoryService {

    private final static Logger log = LoggerFactory.getLogger(RepositoryService.class);
    private final WebClient client;
    @Autowired
    private YAMLConfig config;

    public RepositoryService() {
        this.client = WebClient.create(GITHUB_API_BASE_URI);
    }

    public List<RepositoryModel> getAllRepositoriesByUsername(String username)
            throws WebClientErrorException {

        log.info("Running getAllRepositoriesByUsername service for username: {}.", username);
        String headerName = config.getToken().isEmpty() ? "Unauthorized" : "Authorization";
        return client.get()
                .uri(GITHUB_API_REPOS_URI, username)
                .header(headerName, "token " + config.getToken())
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    throw new WebClientErrorException("/repos. Possible causes: wrong authentication token"
                            + ", exceeded requests per hour limit.", response.rawStatusCode());
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    throw new WebClientErrorException("/repos. Possible causes: internal" +
                            " error of the GitHub API public server.", response.rawStatusCode());
                })
                .bodyToFlux(RepositoryModel.class)
                .timeout(CONN_TIMEOUT)
                .collectList()
                .block();

    }

    public Integer getStarsCountSumByUsername(String username) {
        log.info("Running getStarsCountSumByUsername service for username: {}.", username);
        return getAllRepositoriesByUsername(username)
                .stream()
                .mapToInt(repository -> repository.starsCount)
                .sum();
    }
}
