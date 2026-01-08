package com.kamylo.githubtask;

import java.util.List;

record TaskRepository(String name, String ownerLogin, List<Branch> branches) {
}
