package com.example.repohunter.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class GitHubControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    @DisplayName("Should return 200 OK and list of repositories for existing user")
    void shouldReturnRepositories_whenUserExists() throws Exception {
        mockMvc.perform(get("/api/github/octocat")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$[0].repositoryName").exists())
                .andExpect(jsonPath("$[0].ownerLogin").value("octocat"))
                .andExpect(jsonPath("$[0].branches").isArray());
    }

    @Test
    @DisplayName("Should return 404 NOT FOUND when user does not exist")
    void shouldReturn404_whenUserDoesNotExist() throws Exception {
        String nonExistentUser = "thisUserShouldNotExist123";

        mockMvc.perform(get("/api/github/" + nonExistentUser)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message", containsString(nonExistentUser)));
    }

    @Test
    @DisplayName("Should return valid repository structure for a real GitHub user")
    void shouldReturnValidStructure_whenUserExists() throws Exception {
        String user = "benc-uk";

        mockMvc.perform(get("/api/github/" + user)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].repositoryName").exists())
                .andExpect(jsonPath("$[0].ownerLogin").value(user))
                .andExpect(jsonPath("$[0].branches").isArray())
                .andExpect(jsonPath("$[0].branches[0].name").exists())
                .andExpect(jsonPath("$[0].branches[0].lastCommitSha").exists());
    }

    @Test
    @DisplayName("Should return empty list when user has only forked repositories")
    void shouldReturnEmptyList_whenUserHasOnlyForks() throws Exception {
        String userWithOnlyForks = "forked-only-user";

        mockMvc.perform(get("/api/github/" + userWithOnlyForks)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }


}
