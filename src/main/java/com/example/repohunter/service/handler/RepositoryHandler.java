package com.example.repohunter.service.handler;

import com.example.repohunter.client.GitHubClient;
import com.example.repohunter.client.GitHubRepositoryResponse;
import com.example.repohunter.dto.RepositoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RepositoryHandler {

    private final GitHubClient gitHubClient;
    private final BranchHandler branchHandler;

    public List<RepositoryDto> getNonForkRepositoriesWithBranches(String username) {
        return gitHubClient.getUserRepositories(username).stream()
                .filter(repo -> !repo.isFork())
                .map(this::mapToRepositoryDto)
                .collect(Collectors.toList());
    }

    private RepositoryDto mapToRepositoryDto(GitHubRepositoryResponse repo) {
        return new RepositoryDto(
                repo.getName(),
                repo.getOwner().getLogin(),
                branchHandler.getBranches(repo.getOwner().getLogin(), repo.getName())
        );
    }
}
