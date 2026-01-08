package com.kamylo.githubtask;

class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String username) {
        super("User '%s' not found".formatted(username));
    }
}
