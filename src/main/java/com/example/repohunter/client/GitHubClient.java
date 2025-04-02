package com.example.repohunter.client;

import java.util.List;

public interface GitHubClient {
    List<GitHubRepositoryResponse> getUserRepositories(String username);
    List<GitHubBranchResponse> getBranches(String owner, String repoName);
}