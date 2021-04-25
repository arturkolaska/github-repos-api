# GitHub Repos API
A simple Web API that uses the public GitHub API to list a given user's repositories or to sum stargazers across all
the repositories.

## Technologies
* Java 15
* Spring Boot 2.4.5
* JUnit 5

## General info
* Only HTTP **GET** method is supported.
* All responses are in **JSON** format so 'Accept' header is ignored.
* Query string parameters are ignored as they find no usage for now.
* The app is versioned by URI path to allow easy access through web browser.
* For response statuses **above 300**, a JSON file with *error-code* and *error-message* properties is returned.
  
## Endpoints (*with example responses*)
### GET `/v1/{username}/repos`
Returns a **list of repositories** of given GitHub user.
```json
[
  {
    "name": "functional-programming-workshop-2021",
    "stargazers_count": 0
  },
  {
    "name": "http-clients-workshop-2021",
    "stargazers_count": 0
  }
]
```
<br>

### GET `/v1/{username}/repos/total-stars`
Returns the **total of stargazers** across all repositories of given GitHub user.
```json
{ "total_stargazers_count": 0 }
```
<br>

### GET `/v1`, GET `/`
Return the **links** to other endpoints.
```json
[
    {
        "user_repositories_uri": "https://arturkolaska.herokuapp.com/v1/{username}/repos"
    },
    {
        "user_total_stars_uri": "https://arturkolaska.herokuapp.com/v1/{username}/repos/total-stars"
    }
]
```

## Usage
You can run the online demo or download the repository and run it on your machine.  
To send a request you can use:
* a web browser
* a terminal (with a program like `curl`)
* a client like Postman (using **GET** method -
no additional headers required)

### a) Run the online demo
The app is hosted on [arturkolaska.herokuapp.com](https://arturkolaska.herokuapp.com).
Sample request:  
```
http://arturkolaska.herokuapp.com/v1/allegro/repos
```

### b) Run locally
Go to the repository folder and run `./gradlew bootRun`. The app should now be running on *localhost:8080*.
Sample request:
```
localhost:8080/v1/allegro/repos
```
#### Increase the requests limit (set by *api.github.com*)
To increase the requests limit  from 60 to 5000 per hour,
edit the `application.yml` file (in `src/main/resources/`) and enter your GitHub OAuth
token (or personal access token) into the `token: ` property.

## Improvement ideas
* additional Content-Types of the responses
* *limit* and *start* query parameters
* passing OAuth token through some header in addition to storing it in yaml file