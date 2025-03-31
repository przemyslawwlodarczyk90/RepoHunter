package com.example.repohunter.exceptions;


public class GitHubUserNotFoundException extends RuntimeException {
    public GitHubUserNotFoundException(String username) {
        super("GitHub user not found: " + username);
    }
}
