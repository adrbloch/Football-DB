package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.formerTeam.FormerTeams;
import reactor.core.publisher.Mono;

public interface FormerTeamService {

    Mono<FormerTeams> findFormerTeamsByPlayerId(String playerId);
}
