package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.team.Teams;
import reactor.core.publisher.Mono;

public interface TeamService {
    Mono<Teams> findTeamsByName(String teamName);
}
