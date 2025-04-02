package com.example.repohunter.client;

import com.example.repohunter.exceptions.GitHubUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GitHubClientImpl implements GitHubClient {

    private static final String GITHUB_API_URL = "https://api.github.com";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<GitHubRepositoryResponse> getUserRepositories(String username) {
        String url = GITHUB_API_URL + "/users/" + username + "/repos";
        try {
            GitHubRepositoryResponse[] response = restTemplate.getForObject(url, GitHubRepositoryResponse[].class);
            return Arrays.asList(response);
        } catch (HttpClientErrorException.NotFound e) {
            throw new GitHubUserNotFoundException(username);
        }
    }

    @Override
    public List<GitHubBranchResponse> getBranches(String owner, String repoName) {
        String url = GITHUB_API_URL + "/repos/" + owner + "/" + repoName + "/branches";
        GitHubBranchResponse[] response = restTemplate.getForObject(url, GitHubBranchResponse[].class);
        return Arrays.asList(response);
    }
}
