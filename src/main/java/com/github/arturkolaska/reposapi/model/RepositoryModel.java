package com.github.arturkolaska.reposapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryModel {

    @JsonProperty("name")
    public String name;
    @JsonProperty("stargazers_count")
    public Integer starsCount;

    @Override
    public String toString() {
        return "RepositoryModel{name=" + name + ", starsCount=" + starsCount + "}";
    }

    public String getName() {
        return name;
    }

    public Integer getStarsCount() {
        return starsCount;
    }
}