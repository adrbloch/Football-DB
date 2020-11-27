package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.league.League;
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
class LeagueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchLeaguesByValidCountryAddNotEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/league/results")
                .param("country", "england"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leagues", instanceOf(List.class)))
                .andExpect(model().attribute("leagues", hasSize(greaterThan(0))))
                .andExpect(view().name("results/leagueResults"));
    }

    @Test
    void searchLeaguesByInvalidCountryAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/league/results")
                .param("country", "xxxxxx"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leagues", nullValue()))
                .andExpect(view().name("results/leagueResults"));
    }

    @Test
    void searchLeaguesByEmptyCountryAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/league/results")
                .param("country", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leagues", nullValue()))
                .andExpect(view().name("results/leagueResults"));
    }

    @Test
    void searchLeaguesByValidNameAddLeagueObjectToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/league/results")
                .param("name", "english premier league"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leagues", instanceOf(League.class)))
                .andExpect(view().name("results/leagueResults"));
    }

    @Test
    void searchLeagueByInvalidNameAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/league/results")
                .param("name", "xxxxxx"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leagues", nullValue()))
                .andExpect(view().name("results/leagueResults"));
    }

    @Test
    void searchLeagueByEmptyNameAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/league/results")
                .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leagues", nullValue()))
                .andExpect(view().name("results/leagueResults"));
    }

    @Test
    void searchLeagueByNotSoccerNameAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/league/results")
                .param("name", "NBA"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leagues", nullValue()))
                .andExpect(view().name("results/leagueResults"));
    }


    @Test
    void viewLeagueByIdAddLeagueObjectToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/league/{id}","4328"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("league", instanceOf(League.class)))
                .andExpect(view().name("data/league"));
    }

    @Test
    void viewLeagueByIdAddTeamsListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/league/{id}","4328"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teamsByLeague", instanceOf(List.class)))
                .andExpect(model().attribute("teamsByLeague", hasSize(greaterThan(0))))
                .andExpect(view().name("data/league"));
    }

    @Test
    void viewLeagueByIdAddNullToModelWhenTeamsNotInsertedInAPIAndReturnView() throws Exception {

        mockMvc.perform(get("/league/{id}","4500"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teamsByLeague", nullValue()))
                .andExpect(view().name("data/league"));
    }


    @Test
    void viewLeagueByIdAddTableObjectToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/league/{id}","4328"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teamsByLeague", instanceOf(List.class)))
                .andExpect(model().attribute("teamsByLeague", hasSize(greaterThan(0))))
                .andExpect(view().name("data/league"));
    }

    @Test
    void viewLeagueByIdAddNullToModelWhenTableNotInsertedInAPIAndReturnView() throws Exception {

        mockMvc.perform(get("/league/{id}","4500"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tableByLeague", nullValue()))
                .andExpect(view().name("data/league"));
    }





}