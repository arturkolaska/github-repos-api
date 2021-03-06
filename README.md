# GitHub Repos API

A simple Web API that uses the public GitHub API.  
It can list repositories of a given user and sum stargazers across the repositories.

## Table of Contents

* [Technologies](#technologies)
* [General info](#general-info)
* [Endpoints with sample responses](#endpoints-with-sample-responses)
    + [`/v1/{username}/repos`](#get-v1usernamerepos)
    + [`/v1/{username}/repos/total-stars`](#get-v1usernamerepostotal-stars)
    + [`/v1` `/`](#get-v1-get-)
* [Usage](#usage)
    + [Run the online demo](#a-run-the-online-demo)
    + [Run locally](#b-run-locally)
* [Improvement ideas](#improvement-ideas)

## Technologies

* Java 15
* Gradle
* Spring Boot 2.4.5
* JUnit 5

## General info

* Only HTTP **GET** method is supported.
* All responses are in **JSON** format so 'Accept' header is ignored.
* Query string parameters are ignored as they find no usage for now.
* The app is versioned by URI path to allow easy access through web browser.
* For response statuses **above 300**, a JSON file with *error-code* and *error-message* properties is returned.

## Endpoints (with sample responses)

### GET `/v1/{username}/repos`

Returns a **list of repositories** of given GitHub user.

```json
[
  {
    "name": "my-first-repo",
    "stargazers_count": 20
  },
  {
    "name": "my-second-repo",
    "stargazers_count": 5
  }
]
```

<br>

### GET `/v1/{username}/repos/total-stars`

Returns the **total of stargazers** across all repositories of given GitHub user.

```json
{
  "total_stargazers_count": 25
}
```

<br>

### GET `/v1`, GET `/`

Return **links** to the other endpoints.

```json
[
  {
    "user_repositories_url": "https://arturkolaska.herokuapp.com/v1/{username}/repos"
  },
  {
    "user_total_stars_url": "https://arturkolaska.herokuapp.com/v1/{username}/repos/total-stars"
  }
]
```

## Usage

You can run the online demo or download the repository and run it on your machine.  
To send a request you can use:

* a web browser
* a terminal (with a program like `curl`)
* a client like Postman (no additional headers required)

### a) Run the online demo

The app is hosted on [arturkolaska.herokuapp.com](https://arturkolaska.herokuapp.com).

```
http://arturkolaska.herokuapp.com/v1/allegro/repos/total-stars
```

### b) Run locally

Go to the repository folder and run `./gradlew bootRun`.  
The app should now be running on *localhost:8080*.

```
localhost:8080/v1/allegro/repos/total-stars
```

#### Increase the requests limit (set by *api.github.com*)

To increase the requests limit from 60 to 5000 per hour, edit the `application.yml`
file (in `src/main/resources/`) and enter your GitHub OAuth token (or personal access token)
into the `token: ` property.  
If you exceed the hourly limit for your IP address, you will get the following message:

```json
{
  "error-code": 403,
  "error-message": "FORBIDDEN"
}
```

## Improvement ideas

* additional Content-Types of the responses
* *limit* and *start* query parameters
* passing OAuth token through some header in addition to storing it in yaml file
