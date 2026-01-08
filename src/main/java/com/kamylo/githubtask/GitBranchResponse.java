package com.kamylo.githubtask;

record GitBranchResponse(
        String name,
        Commit commit
) {
    record Commit(String sha) {
    }
}
