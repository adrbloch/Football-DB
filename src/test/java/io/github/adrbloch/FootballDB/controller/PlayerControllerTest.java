package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.contract.Contract;
import io.github.adrbloch.FootballDB.model.player.Player;
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
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchPlayersByValidNameAddNotEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/results")
                .param("name", "ronaldo"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", instanceOf(List.class)))
                .andExpect(model().attribute("players", hasSize(greaterThan(0))))
                .andExpect(view().name("results/playerResults"));
    }

    @Test
    void searchPlayersByInvalidNameAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/results")
                .param("name", "xxxxxx"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", nullValue()))
                .andExpect(view().name("results/playerResults"));
    }

    @Test
    void searchPlayersByEmptyNameAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/results")
                .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", nullValue()))
                .andExpect(view().name("results/playerResults"));
    }

    @Test
    void searchPlayersByNotSoccerPlayerAddEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/results")
                .param("name", "LeBron James"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", IsEmptyCollection.empty()))
                .andExpect(view().name("results/playerResults"));
    }

    @Test
    void searchPlayersByValidTeamAndNameAddNotEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/results")
                .param("team", "juventus")
                .param("name", "ronaldo"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", instanceOf(List.class)))
                .andExpect(model().attribute("players", hasSize(greaterThan(0))))
                .andExpect(view().name("results/playerResults"));
    }

    @Test
    void searchPlayersByInvalidTeamAndNameAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/results")
                .param("team", "xxxxxx")
                .param("name", "xxxxxx"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", nullValue()))
                .andExpect(view().name("results/playerResults"));
    }

    @Test
    void searchPlayersByEmptyTeamAndNameAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/results")
                .param("team", "")
                .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", nullValue()))
                .andExpect(view().name("results/playerResults"));
    }

    @Test
    void searchPlayersByEmptyTeamAndNotEmptyNameAddListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/results")
                .param("team", "")
                .param("name", "ronaldo"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", instanceOf(List.class)))
                .andExpect(model().attribute("players", hasSize(greaterThan(0))))
                .andExpect(view().name("results/playerResults"));
    }

    @Test
    void searchPlayersByNotEmptyTeamAndEmptyNameAddListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/results")
                .param("team", "juventus")
                .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", instanceOf(List.class)))
                .andExpect(model().attribute("players", hasSize(greaterThan(0))))
                .andExpect(view().name("results/playerResults"));
    }

    @Test
    void searchPlayersByNotSoccerPlayerAndTeamAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/results")
                .param("team", "Los Angeles Lakers")
                .param("name", "LeBron James"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", nullValue()))
                .andExpect(view().name("results/playerResults"));
    }

    @Test
    void viewPlayerByIdAddPlayerObjectToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/{id}","34146304"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("player", instanceOf(Player.class)))
                .andExpect(view().name("data/player"));
    }

    @Test
    void viewPlayerByIdAddContractObjectToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/{id}","34146304"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("contract", instanceOf(Contract.class)))
                .andExpect(view().name("data/player"));
    }

    @Test
    void viewRetiredPlayerByIdAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/{id}","34161040"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("contract", nullValue()))
                .andExpect(view().name("data/player"));
    }

    @Test
    void viewPlayerByIdAddFormerTeamsListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/{id}","34146304"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("formerTeams", instanceOf(List.class)))
                .andExpect(model().attribute("formerTeams", hasSize(greaterThan(0))))
                .andExpect(view().name("data/player"));
    }

    @Test
    void viewPlayerByIdAddNullTFormerTeamModelWhenUnknownAndReturnView() throws Exception {

        mockMvc.perform(get("/player/{id}","34157398"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("formerTeams", nullValue()))
                .andExpect(view().name("data/player"));
    }

    @Test
    void viewPlayerByIdAddHonorListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/player/{id}","34146304"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("honors", instanceOf(List.class)))
                .andExpect(model().attribute("honors", hasSize(greaterThan(0))))
                .andExpect(view().name("data/player"));
    }

    @Test
    void viewPlayerByIdAddNullToModelWhenUnknownAndReturnView() throws Exception {

        mockMvc.perform(get("/player/{id}","34157398"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("honors", nullValue()))
                .andExpect(view().name("data/player"));
    }



}