package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.match.Match;
import io.github.adrbloch.FootballDB.model.match.Matches;
import io.github.adrbloch.FootballDB.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);
    private final WebClient webClient;

    @Autowired
    public MatchServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<List<Match>> findMatchesByTeams(String homeTeam, String awayTeam) {

        logger.info("Getting matches by teams - home: [{}] and away: [{}]", homeTeam, awayTeam);

        Optional<List<Match>> matchesByTeams = Optional.empty();

        if (!homeTeam.isEmpty() && !awayTeam.isEmpty()) {

            matchesByTeams = Optional.ofNullable(
                    webClient.get()
                            .uri("/searchevents.php?e=" + homeTeam + " vs " + awayTeam)
                            .retrieve()
                            .bodyToMono(Matches.class)
                            .block()
                            .getMatches())
                    .map(list -> list
                            .stream()
                            .filter(m -> m.getStrSport().equals(("Soccer")))
                            .sorted(Comparator
                                    .comparing(Match::getDateEvent)
                                    .reversed())
                            .collect(Collectors.toList()));
        }

        return matchesByTeams;
    }

    @Override
    public Optional<List<Match>> findMatchesByTeamsAndSeason(String homeTeam, String awayTeam, String season) {

        logger.info("Getting matches by teams - home: [{}], away: [{}] and season: [{}]", homeTeam, awayTeam, season);

        Optional<List<Match>> matchesByTeamsAndSeason = Optional.empty();

        if (!homeTeam.isEmpty() && !awayTeam.isEmpty()) {

            matchesByTeamsAndSeason = Optional.ofNullable(
                    webClient.get()
                            .uri("/searchevents.php?e=" + homeTeam + " vs " + awayTeam + "&s=" + season)
                            .retrieve()
                            .bodyToMono(Matches.class)
                            .block()
                            .getMatches())
                    .map(list -> list
                            .stream()
                            .filter(m -> m.getStrSport().equals(("Soccer")))
                            .sorted(Comparator
                                    .comparing(Match::getDateEvent)
                                    .reversed())
                            .collect(Collectors.toList()));
        }

        return matchesByTeamsAndSeason;
    }

    @Override
    public Match findMatchById(String id) {

        logger.info("Getting match by id: [{}]", id);

        return webClient.get()
                .uri("/lookupevent.php?id=" + id)
                .retrieve()
                .bodyToMono(Matches.class)
                .block()
                .getMatchById()
                .get(0);
    }

}