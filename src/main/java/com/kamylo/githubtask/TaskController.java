package com.kamylo.githubtask;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class TaskController {
    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/users/{username}/repositories", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TaskRepository> getUserRepositories(@PathVariable String username) {
        return taskService.getUserRepositories(username);
    }
}
