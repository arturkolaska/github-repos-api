package com.github.arturkolaska.reposapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryModel {

    @JsonProperty("name")
    private String name;
    @JsonProperty("stargazers_count")
    private Integer starsCount;

    public String getName() {
        return name;
    }

    public Integer getStarsCount() {
        return starsCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, starsCount);
    }

    @Override
    public String toString() {
        return "RepositoryModel{name=" + name + ", starsCount=" + starsCount + "}";
    }
}