package com.example.repohunter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDto {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchDto> branches;
}
