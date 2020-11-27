package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.match.Match;
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
class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchMatchesByValidTeamsAddNotEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "arsenal")
                .param("awayTeam", "chelsea"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", instanceOf(List.class)))
                .andExpect(model().attribute("matches", hasSize(greaterThan(0))))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesByInvalidHomeTeamAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "xxxxxxxx")
                .param("awayTeam", "chelsea"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesByInvalidAwayTeamAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "arsenal")
                .param("awayTeam", "xxxxxxxx"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesByInvalidTeamsAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "xxxxxxxx")
                .param("awayTeam", "xxxxxxxx"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesByEmptyHomeTeamAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "")
                .param("awayTeam", "chelsea"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesByEmptyAwayTeamAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "arsenal")
                .param("awayTeam", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesByEmptyTeamsAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "")
                .param("awayTeam", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesByNotSoccerTeamsAddEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "Chicago Bulls")
                .param("awayTeam", "Cleveland Cavaliers"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", IsEmptyCollection.empty()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesByValidTeamsAndSeasonAddNotEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "arsenal")
                .param("awayTeam", "chelsea")
                .param("season", "2016-2017"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", instanceOf(List.class)))
                .andExpect(model().attribute("matches", hasSize(greaterThan(0))))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesByInvalidHomeTeamAndValidAwayTeamAndSeasonAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "xxxxxxxx")
                .param("awayTeam", "chelsea")
                .param("season", "2016-2017"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesByInvalidAwayTeamAndValidHomeTeamAndSeasonAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "arsenal")
                .param("awayTeam", "xxxxxxxx")
                .param("season", "2016-2017"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesBySeasonAndInvalidTeamsAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "xxxxxxxx")
                .param("awayTeam", "xxxxxxxx")
                .param("season", "2016-2017"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesBySeasonAndEmptyHomeTeamAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "")
                .param("awayTeam", "chelsea")
                .param("season", "2016-2017"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesBySeasonAndEmptyAwayTeamAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "arsenal")
                .param("awayTeam", "")
                .param("season", "2016-2017"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesBySeasonAndEmptyTeamsAddNullToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "")
                .param("awayTeam", "")
                .param("season", "2016-2017"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", nullValue()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void searchMatchesBySeasonAndNotSoccerTeamsAddEmptyListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/results")
                .param("homeTeam", "Chicago Bulls")
                .param("awayTeam", "Cleveland Cavaliers")
                .param("season", "2016-2017"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("matches", IsEmptyCollection.empty()))
                .andExpect(view().name("results/matchResults"));
    }

    @Test
    void viewMatchByIdAddMatchObjectToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/{id}","1009638"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("match", instanceOf(Match.class)))
                .andExpect(view().name("data/match"));
    }

    @Test
    void viewMatchByIdAddHomeGoalListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/{id}","1009638"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("homeGoalList", instanceOf(List.class)))
                .andExpect(model().attribute("homeGoalList", hasSize(greaterThan(0))))
                .andExpect(view().name("data/match"));
    }

    @Test
    void viewMatchByIdAddEmptyListModelWhenHomeGoalsAreNotInsertedInyAPIAndReturnView() throws Exception {

        mockMvc.perform(get("/match/{id}","1032832"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("homeGoalList", IsEmptyCollection.empty()))
                .andExpect(view().name("data/match"));
    }

    @Test
    void viewMatchByIdAddAwatGoalListToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/{id}","1009638"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("awayGoalList", instanceOf(List.class)))
                .andExpect(model().attribute("awayGoalList", hasSize(greaterThan(0))))
                .andExpect(view().name("data/match"));
    }

    @Test
    void viewMatchByIdAddEmptyListModelWhenAwayGoalsAreNotInsertedInAPIAndReturnView() throws Exception {

        mockMvc.perform(get("/match/{id}","1032832"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("awayGoalList", IsEmptyCollection.empty()))
                .andExpect(view().name("data/match"));
    }

    @Test
    void viewMatchByIdAddHomeTeamToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/{id}","1009638"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("homeTeam", instanceOf(Team.class)))
                .andExpect(view().name("data/match"));
    }


    @Test
    void viewMatchByIdAddAwayTeamToModelAndReturnView() throws Exception {

        mockMvc.perform(get("/match/{id}","1009638"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("awayTeam", instanceOf(Team.class)))
                .andExpect(view().name("data/match"));
    }
}