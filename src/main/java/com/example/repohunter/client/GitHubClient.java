package com.example.repohunter.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GitHubClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<GitHubRepositoryResponse> getUserRepositories(String username) {
        try {
            var url = "https://api.github.com/users/" + username + "/repos";
            GitHubRepositoryResponse[] response = restTemplate.getForObject(url, GitHubRepositoryResponse[].class);
            return Arrays.asList(response);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("User not found");
        }
    }

    public List<GitHubBranchResponse> getBranches(String owner, String repoName) {
        var url = "https://api.github.com/repos/" + owner + "/" + repoName + "/branches";
        GitHubBranchResponse[] response = restTemplate.getForObject(url, GitHubBranchResponse[].class);
        return Arrays.asList(response);
    }
}
