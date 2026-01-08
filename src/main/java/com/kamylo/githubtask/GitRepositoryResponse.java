package com.kamylo.githubtask;

record GitRepositoryResponse(
        String name,
        Owner owner,
        boolean fork
) {
    record Owner(String login) {
    }
}
