package com.example.repohunter.client;

import com.example.repohunter.exceptions.GitHubUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GitHubClientImpl implements GitHubClient {

    @Value("${github.api.url}")
    private String gitHubApiUrl;

    @Value("${github.api.token}")
    private String gitHubToken;

    private final RestTemplate restTemplate = new RestTemplate();

    private <T> List<T> getFromGitHub(String url, Class<T[]> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + gitHubToken);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<T[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<GitHubRepositoryResponse> getUserRepositories(String username) {
        String url = gitHubApiUrl + "/users/" + username + "/repos";
        try {
            return getFromGitHub(url, GitHubRepositoryResponse[].class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new GitHubUserNotFoundException(username);
        }
    }

    @Override
    public List<GitHubBranchResponse> getBranches(String owner, String repoName) {
        String url = gitHubApiUrl + "/repos/" + owner + "/" + repoName + "/branches";
        return getFromGitHub(url, GitHubBranchResponse[].class);
    }
}
