package com.example.repohunter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto implements Serializable {
    private String name;
    private String lastCommitSha;
}
