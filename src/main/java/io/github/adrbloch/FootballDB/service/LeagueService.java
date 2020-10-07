package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.league.Leagues;
import reactor.core.publisher.Mono;

public interface LeagueService {

    Mono<Leagues> findLeaguesByCountry(String country);

    Mono<Leagues> findLeagueById(String id);
}
