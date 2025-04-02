package com.example.repohunter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDto implements Serializable {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchDto> branches;
}
