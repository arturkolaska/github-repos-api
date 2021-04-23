package com.github.arturkolaska.reposapi.service;

import com.github.arturkolaska.reposapi.model.RepositoryModel;

import java.util.List;


public interface RepositoryService {

    List<RepositoryModel> getAllRepositoriesByUsername(String username);

    Integer getTotalStarsByUsername(String username);
}
