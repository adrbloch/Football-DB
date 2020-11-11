package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.match.Matches;
import reactor.core.publisher.Mono;

public interface MatchService {

    Mono<Matches> findMatchesByTeams(String homeTeam, String awayTeam);

    Mono<Matches> findMatchesByTeamsAndSeason(String homeTeam, String awayTeam, String season);

    Mono<Matches> findMatchById(String id);

    Mono<Matches> findNext5MatchesByTeamId(String teamId);

    Mono<Matches> findNext15MatchesByLeagueId(String leagueId);

    Mono<Matches> findLast5MatchesByTeamId(String teamId);

    Mono<Matches> findLast15MatchesByLeagueId(String leagueId);

    Mono<Matches> findMatchesOfRoundByLeagueIdAndRoundAndSeason(String leagueId, String round, String season);

}
