package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.event.Events;
import reactor.core.publisher.Mono;

public interface EventService {

    Mono<Events> findEventsByName(String eventName);

    Mono<Events> findEventByNameAndSeason(String eventName, String season);

    Mono<Events> findEventById(String id);

    Mono<Events> findNext5EventsByTeamId(String teamId);

    Mono<Events> findNext15EventsByLeagueId(String leagueId);

    Mono<Events> findLast5EventsByTeamId(String teamId);

    Mono<Events> findLast15EventsByLeagueId(String leagueId);

    Mono<Events> findEventsOfRoundByLeagueIdAndRoundAndSeason(String leagueId, String round, String season);

}
