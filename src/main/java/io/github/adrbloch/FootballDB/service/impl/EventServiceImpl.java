package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.event.Events;
import io.github.adrbloch.FootballDB.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public EventServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Events> findEventsByName(String eventName) {
        logger.info("Getting events by name: [" + eventName + "]");

        return webClient.get()
                .uri("/searchevents.php?t=" + eventName)
                .retrieve()
                .bodyToMono(Events.class);
    }

    @Override
    public Mono<Events> findEventByNameAndSeason(String eventName, String season) {
        logger.info("Getting events by name: [{}] and season: [{}]", eventName, season);

        return webClient.get()
                .uri("/searchevents.php?e=" + eventName + "&s=" + season)
                .retrieve()
                .bodyToMono(Events.class);
    }

    @Override
    public Mono<Events> findEventById(String id) {
        logger.info("Getting event by id: [{}]", id);

        return webClient.get()
                .uri("/lookupevent.php?id=" + id)
                .retrieve()
                .bodyToMono(Events.class);
    }

    @Override
    public Mono<Events> findNext5EventsByTeamId(String teamId) {
        logger.info("Getting next 5 events by team id: [{}]", teamId);

        return webClient.get()
                .uri("/eventsnext.php?id=" + teamId)
                .retrieve()
                .bodyToMono(Events.class);
    }

    @Override
    public Mono<Events> findNext15EventsByLeagueId(String leagueId) {
        logger.info("Getting next 15 events by league id: [{}]", leagueId);

        return webClient.get()
                .uri("/eventsnextleague.php?id=" + leagueId)
                .retrieve()
                .bodyToMono(Events.class);
    }

    @Override
    public Mono<Events> findLast5EventsByTeamId(String teamId) {
        logger.info("Getting last 5 events by team id: [{}]", teamId);

        return webClient.get()
                .uri("/eventslast.php?id=" + teamId)
                .retrieve()
                .bodyToMono(Events.class);
    }

    @Override
    public Mono<Events> findLast15EventsByLeagueId(String leagueId) {
        logger.info("Getting last 15 events by league id: [{}]", leagueId);

        return webClient.get()
                .uri("/eventspastleague.php?id=" + leagueId)
                .retrieve()
                .bodyToMono(Events.class);
    }

    @Override
    public Mono<Events> findEventsOfRoundByLeagueIdAndRoundAndSeason(String leagueId, String round, String season) {
        logger.info("Getting events of a round by league id: [{}], round: [{}], season: [{}]", leagueId, round, season);

        return webClient.get()
                .uri("/eventsround.php?id=" + leagueId + "&r=" + round + "&s=" + season)
                .retrieve()
                .bodyToMono(Events.class);
    }
}