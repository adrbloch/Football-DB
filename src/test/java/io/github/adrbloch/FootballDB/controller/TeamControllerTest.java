package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.team.Team;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = "ADMIN")
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchTeamsByValidCountryAddNotEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/results")
                .param("country", "england"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teams", instanceOf(List.class)))
                .andExpect(model().attribute("teams", hasSize(greaterThan(0))))
                .andExpect(view().name("results/teamResults"));
    }

    @Test
    void searchTeamsByInvalidCountryAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/results")
                .param("country", "xxxxxx"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teams", nullValue()))
                .andExpect(view().name("results/teamResults"));
    }

    @Test
    void searchTeamsByEmptyCountryAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/results")
                .param("country", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teams", nullValue()))
                .andExpect(view().name("results/teamResults"));
    }

    @Test
    void searchTeamsByValidLeagueAddNotEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/results")
                .param("league", "english premier league"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teams", instanceOf(List.class)))
                .andExpect(model().attribute("teams", hasSize(greaterThan(0))))
                .andExpect(view().name("results/teamResults"));
    }

    @Test
    void searchTeamsByInvalidLeagueAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/results")
                .param("league", "xxxxxx"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teams", nullValue()))
                .andExpect(view().name("results/teamResults"));
    }

    @Test
    void searchTeamsByEmptyLeagueAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/results")
                .param("league", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teams", nullValue()))
                .andExpect(view().name("results/teamResults"));
    }

    @Test
    void searchTeamsByNotSoccerLeagueAddEmptyCollectionToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/results")
                .param("league", "NBA"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teams", IsEmptyCollection.empty()))
                .andExpect(view().name("results/teamResults"));
    }

    @Test
    void searchTeamsByValidNameAddNotEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/results")
                .param("name", "liverpool"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teams", instanceOf(List.class)))
                .andExpect(model().attribute("teams", hasSize(greaterThan(0))))
                .andExpect(view().name("results/teamResults"));
    }

    @Test
    void searchTeamsByInvalidNameAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/results")
                .param("name", "xxxxxx"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teams", nullValue()))
                .andExpect(view().name("results/teamResults"));
    }

    @Test
    void searchTeamsByEmptyNameAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/results")
                .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teams", nullValue()))
                .andExpect(view().name("results/teamResults"));
    }

    @Test
    void searchTeamsByNotSoccerNameAddEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/results")
                .param("name", "Chicago Bulls"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teams", IsEmptyCollection.empty()))
                .andExpect(view().name("results/teamResults"));
    }

    @Test
    void viewTeamDetailsByTeamIdAddTeamObjectToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/{id}","133602"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("team", instanceOf(Team.class)))
                .andExpect(view().name("data/team"));
    }

    @Test
    void viewTeamDetailsByTeamIdAddNotNullLeagueListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/team/{id}","133602"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leaguesByTeam", not(contains(nullValue()))))
                .andExpect(view().name("data/team"));
    }

}