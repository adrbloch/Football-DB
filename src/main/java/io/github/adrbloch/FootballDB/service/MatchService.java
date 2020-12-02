package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.match.Match;

import java.util.List;
import java.util.Optional;

public interface MatchService {

    Optional<List<Match>> findMatchesByTeams(String homeTeam, String awayTeam);

    Optional<List<Match>> findMatchesByTeamsAndSeason(String homeTeam, String awayTeam, String season);

    Match findMatchById(String id);

}
