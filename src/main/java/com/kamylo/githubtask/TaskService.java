package com.kamylo.githubtask;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TaskService {

    private final GithubClient gitHubClient;

    TaskService(GithubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    List<TaskRepository> getUserRepositories(String username) {
        var repositories = gitHubClient.getRepositories(username);

        return repositories.stream()
                .filter(repo -> !repo.fork())
                .map(repo -> {
                    var branches = gitHubClient.getBranches(username, repo.name());
                    var branchList = branches.stream()
                            .map(branch -> new Branch(branch.name(), branch.commit().sha()))
                            .toList();
                    return new TaskRepository(repo.name(), repo.owner().login(), branchList);
                })
                .toList();
    }
}