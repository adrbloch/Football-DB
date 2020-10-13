package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.match.Matches;
import io.github.adrbloch.FootballDB.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MatchServiceImpl implements MatchService {

    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public MatchServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Matches> findMatchesByName(String eventName) {
        logger.info("Getting matches by name: [" + eventName + "]");

        return webClient.get()
                .uri("/searchevents.php?t=" + eventName)
                .retrieve()
                .bodyToMono(Matches.class);
    }

    @Override
    public Mono<Matches> findMatchByNameAndSeason(String eventName, String season) {
        logger.info("Getting matches by name: [{}] and season: [{}]", eventName, season);

        return webClient.get()
                .uri("/searchevents.php?e=" + eventName + "&s=" + season)
                .retrieve()
                .bodyToMono(Matches.class);
    }

    @Override
    public Mono<Matches> findMatchById(String id) {
        logger.info("Getting match by id: [{}]", id);

        return webClient.get()
                .uri("/lookupevent.php?id=" + id)
                .retrieve()
                .bodyToMono(Matches.class);
    }

    @Override
    public Mono<Matches> findNext5MatchesByTeamId(String teamId) {
        logger.info("Getting next 5 matches by team id: [{}]", teamId);

        return webClient.get()
                .uri("/eventsnext.php?id=" + teamId)
                .retrieve()
                .bodyToMono(Matches.class);
    }

    @Override
    public Mono<Matches> findNext15MatchesByLeagueId(String leagueId) {
        logger.info("Getting next 15 matches by league id: [{}]", leagueId);

        return webClient.get()
                .uri("/eventsnextleague.php?id=" + leagueId)
                .retrieve()
                .bodyToMono(Matches.class);
    }

    @Override
    public Mono<Matches> findLast5MatchesByTeamId(String teamId) {
        logger.info("Getting last 5 matches by team id: [{}]", teamId);

        return webClient.get()
                .uri("/eventslast.php?id=" + teamId)
                .retrieve()
                .bodyToMono(Matches.class);
    }

    @Override
    public Mono<Matches> findLast15MatchesByLeagueId(String leagueId) {
        logger.info("Getting last 15 matches by league id: [{}]", leagueId);

        return webClient.get()
                .uri("/eventspastleague.php?id=" + leagueId)
                .retrieve()
                .bodyToMono(Matches.class);
    }

    @Override
    public Mono<Matches> findMatchesOfRoundByLeagueIdAndRoundAndSeason(String leagueId, String round, String season) {
        logger.info("Getting matches of a round by league id: [{}], round: [{}], season: [{}]", leagueId, round, season);

        return webClient.get()
                .uri("/eventsround.php?id=" + leagueId + "&r=" + round + "&s=" + season)
                .retrieve()
                .bodyToMono(Matches.class);
    }
}