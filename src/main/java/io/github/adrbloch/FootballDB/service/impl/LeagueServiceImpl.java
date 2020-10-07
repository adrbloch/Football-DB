package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.league.Leagues;
import io.github.adrbloch.FootballDB.service.LeagueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LeagueServiceImpl implements LeagueService {

    private static final Logger logger = LoggerFactory.getLogger(LeagueServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public LeagueServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Leagues> findLeaguesByCountry(String country) {
        logger.info("Getting leagues by name: [" + country + "]");

        return webClient.get()
                .uri("/search_all_leagues.php?c=" + country + "&s=Soccer")
                .retrieve()
                .bodyToMono(Leagues.class);
    }

    @Override
    public Mono<Leagues> findLeagueById(String id) {
        logger.info("Getting league by id: [" + id + "]");

        return webClient.get()
                .uri("/lookupleague.php?id=" + id)
                .retrieve()
                .bodyToMono(Leagues.class);
    }
}
