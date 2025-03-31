package com.example.repohunter.service.facade;

import com.example.repohunter.dto.RepositoryDto;
import com.example.repohunter.service.handler.RepositoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GitHubFacade {

    private final RepositoryHandler repositoryHandler;

    public List<RepositoryDto> getUserRepositories(String username) {
        return repositoryHandler.getNonForkRepositoriesWithBranches(username);
    }
}
