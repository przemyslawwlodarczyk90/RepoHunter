package com.example.repohunter.controller;

import com.example.repohunter.dto.RepositoryDto;
import com.example.repohunter.service.facade.GitHubFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GitHubController {

    private final GitHubFacade gitHubFacade;


    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryDto>> getUserRepositories(@PathVariable String username) {
        List<RepositoryDto> repositories = gitHubFacade.getUserRepositories(username);
        return ResponseEntity.ok(repositories);
    }
}
