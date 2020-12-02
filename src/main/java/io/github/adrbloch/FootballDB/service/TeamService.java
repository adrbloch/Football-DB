package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.team.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    Optional<List<Team>> findTeamsByName(String teamName);

    Optional<List<Team>> findTeamsByLeague(String league);

    Optional<List<Team>> findTeamsByCountry(String country);

    Optional<Team> findTeamById(String id);
}
