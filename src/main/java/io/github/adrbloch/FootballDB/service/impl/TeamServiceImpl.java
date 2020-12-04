package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.team.Team;
import io.github.adrbloch.FootballDB.model.team.Teams;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
    private final WebClient webClient;

    @Autowired
    public TeamServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<List<Team>> findTeamsByName(String teamName) {

        logger.info("Getting teams by name: [" + teamName + "]");

        Optional<List<Team>> teamsByName = Optional.empty();

        if (!teamName.isEmpty()) {

            teamsByName = Optional.ofNullable(
                    webClient.get()
                            .uri("/searchteams.php?t=" + teamName)
                            .retrieve()
                            .bodyToMono(Teams.class)
                            .block()
                            .getTeams())
                    .map(list -> list
                            .stream()
                            .filter(t -> t.getStrSport().equals(("Soccer")))
                            .collect(Collectors.toList()));
        }

        return teamsByName;
    }

    @Override
    public Optional<List<Team>> findTeamsByLeague(String league) {

        logger.info("Getting teams by league: [" + league + "]");

        Optional<List<Team>> teamsByLeague = Optional.empty();

        if (!league.isEmpty()) {

            teamsByLeague = Optional.ofNullable(
                    webClient.get()
                            .uri("/search_all_teams.php?l=" + league)
                            .retrieve()
                            .bodyToMono(Teams.class)
                            .block()
                            .getTeams())
                    .map(list -> list
                            .stream()
                            .filter(t -> t.getStrSport().equals(("Soccer")))
                            .collect(Collectors.toList()));
        }

        return teamsByLeague;
    }

    @Override
    public Optional<List<Team>> findTeamsByCountry(String country) {

        logger.info("Getting teams by country: [" + country + "]");

        return Optional.ofNullable(
                webClient
                        .get()
                        .uri("/search_all_teams.php?s=Soccer&c=" + country)
                        .retrieve()
                        .bodyToMono(Teams.class)
                        .block()
                        .getTeams())
                .map(list -> list
                        .stream()
                        .filter(t -> t.getStrSport().equals(("Soccer")))
                        .collect(Collectors.toList()));
    }

    @Override
    public Team findTeamById(String id) {

        logger.info("Getting team by id: [" + id + "]");

        return webClient.get()
                        .uri("/lookupteam.php?id=" + id)
                        .retrieve()
                        .bodyToMono(Teams.class)
                        .block()
                        .getTeams()
                        .get(0);
    }
}
