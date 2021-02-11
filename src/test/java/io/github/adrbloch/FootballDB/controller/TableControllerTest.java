package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.league.League;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = "ADMIN")
@ActiveProfiles("test")
class TableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchTablesByLeagueAndSeasonAddObjectToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/table/results")
                .param("league", "english premier league")
                .param("season", "2020-2021"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leagues", instanceOf(League.class)))
                .andExpect(model().attribute("season", equalTo("2020-2021")))
                .andExpect(view().name("results/tableResults"));
    }

    @Test
    void searchTablesByEmptyLeagueAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/table/results")
                .param("league", "")
                .param("season", "2016-2017"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leagues", equalTo(null)))
                .andExpect(model().attribute("season", equalTo("2016-2017")))
                .andExpect(view().name("results/tableResults"));
    }


    @Test
    void searchTablesByInvalidLeagueAddNullToLeaguesModelAndReturnView() throws Exception {

        mockMvc.perform(get("/table/results")
                .param("league", "xxx")
                .param("season", "2016-2017"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leagues", equalTo(null)))
                .andExpect(model().attribute("season", equalTo("2016-2017")))
                .andExpect(view().name("results/tableResults"));
    }

    @Test
    void searchTablesByNotSoccerLeagueAddNullToLeaguesModelAndReturnView() throws Exception {

        mockMvc.perform(get("/table/results")
                .param("league", "NBA")
                .param("season", "2016-2017"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("leagues", equalTo(null)))
                .andExpect(model().attribute("season", equalTo("2016-2017")))
                .andExpect(view().name("results/tableResults"));
    }

    @Test
    void viewTableByLeagueIdAndSeasonAddObjectToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/table")
                .param("leagueId", "4328")
                .param("season", "2020-2021"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("table", instanceOf(List.class)))
                .andExpect(model().attribute("table", hasSize(20)))
                .andExpect(model().attribute("league", instanceOf(League.class)))
                .andExpect(model().attribute("season", equalTo("2020-2021")))
                .andExpect(view().name("data/table"));
    }


}