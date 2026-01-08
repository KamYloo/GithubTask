package com.kamylo.githubtask;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
class GithubClient {
    private final RestClient restClient;

    GithubClient(RestClient gitHubRestClient) {
        this.restClient = gitHubRestClient;
    }

    List<GitRepositoryResponse> getRepositories(String username) {
        return restClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (_, response) -> {
                    if (response.getStatusCode().value() == 404) {
                        throw new UserNotFoundException(username);
                    }
                })
                .body(new ParameterizedTypeReference<>() {});
    }

    List<GitBranchResponse> getBranches(String username, String repositoryName) {
        return restClient.get()
                .uri("/repos/{username}/{repo}/branches", username, repositoryName)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
