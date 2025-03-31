package com.example.repohunter.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubBranchResponse {
    private String name;
    private Commit commit;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Commit {
        private String sha;
    }
}
