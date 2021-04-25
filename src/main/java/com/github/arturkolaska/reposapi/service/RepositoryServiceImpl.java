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

import static com.github.arturkolaska.reposapi.constants.NumericConstants.CONN_TIMEOUT;
import static com.github.arturkolaska.reposapi.constants.StringConstants.GITHUB_API_BASE_URI;
import static com.github.arturkolaska.reposapi.constants.StringConstants.GITHUB_API_REPOS_URI;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    private final static Logger log = LoggerFactory.getLogger(RepositoryServiceImpl.class);
    private final WebClient client;

    @Autowired
    private YAMLConfig config;

    public RepositoryServiceImpl() {
        this.client = WebClient.builder()
                .defaultHeader(ACCEPT, APPLICATION_JSON_VALUE)
                .baseUrl(GITHUB_API_BASE_URI).build();
    }

    @Override
    public List<RepositoryModel> getAllRepositoriesByUsername(String username) throws WebClientErrorException {
        log.info("Running getAllRepositoriesByUsername service for username={}.", username);
        return client
                .get()
                .uri(GITHUB_API_REPOS_URI, username)
                .header(config.getAuthorizationHeader(), config.getToken())
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    throw new WebClientErrorException(response.rawStatusCode());
                })
                .bodyToFlux(RepositoryModel.class)
                .timeout(CONN_TIMEOUT)
                .collectList().block();
    }

    @Override
    public Integer getTotalStarsByUsername(String username) {
        log.info("Running getStarsCountSumByUsername service for username={}.", username);
        return getAllRepositoriesByUsername(username)
                .stream()
                .mapToInt(RepositoryModel::getStarsCount)
                .sum();
    }
}
