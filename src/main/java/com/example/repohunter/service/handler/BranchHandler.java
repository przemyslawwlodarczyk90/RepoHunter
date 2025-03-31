package com.example.repohunter.service.handler;

import com.example.repohunter.client.GitHubBranchResponse;
import com.example.repohunter.client.GitHubClient;
import com.example.repohunter.dto.BranchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class BranchHandler {

    private final GitHubClient gitHubClient;

    public List<BranchDto> getBranches(String owner, String repoName) {
        List<GitHubBranchResponse> branches = gitHubClient.getBranches(owner, repoName);

        return branches.stream()
                .map(branch -> new BranchDto(branch.getName(), branch.getCommit().getSha()))
                .collect(Collectors.toList());
    }
}
