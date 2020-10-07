package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.player.Players;
import reactor.core.publisher.Mono;

public interface PlayerService {

    Mono<Players> findPlayersByName(String playerName);

    Mono<Players> findPlayersByTeamAndName(String teamName, String playerName);
}
