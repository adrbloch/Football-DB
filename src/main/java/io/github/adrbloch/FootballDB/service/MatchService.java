package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.match.Matches;
import reactor.core.publisher.Mono;

public interface MatchService {

    Mono<Matches> findMatchesByName(String eventName);

    Mono<Matches> findMatchByNameAndSeason(String eventName, String season);

    Mono<Matches> findMatchById(String id);

    Mono<Matches> findNext5MatchesByTeamId(String teamId);

    Mono<Matches> findNext15MatchesByLeagueId(String leagueId);

    Mono<Matches> findLast5MatchesByTeamId(String teamId);

    Mono<Matches> findLast15MatchesByLeagueId(String leagueId);

    Mono<Matches> findMatchesOfRoundByLeagueIdAndRoundAndSeason(String leagueId, String round, String season);

}
