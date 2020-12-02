package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.league.League;
import io.github.adrbloch.FootballDB.model.league.Leagues;
import io.github.adrbloch.FootballDB.service.LeagueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeagueServiceImpl implements LeagueService {

    private static final Logger logger = LoggerFactory.getLogger(LeagueServiceImpl.class);
    private final WebClient webClient;

    @Autowired
    public LeagueServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<List<League>> findLeaguesByCountry(String country) {

        logger.info("Getting leagues by country: [" + country + "]");

        return Optional.ofNullable(
                webClient.get()
                        .uri("/search_all_leagues.php?c=" + country + "&s=Soccer")
                        .retrieve()
                        .bodyToMono(Leagues.class)
                        .block()
                        .getLeaguesByCountry())
                .map(list -> list
                        .stream()
                        .filter(l -> l.getStrSport().equals(("Soccer")))
                        .collect(Collectors.toList()));
    }

    @Override
    public Optional<League> findLeagueByName(String name) {

        logger.info("Getting league by name: [" + name + "]");

        Optional<League> leagueByName;

        try {
            leagueByName = Optional.ofNullable(
                    webClient.get()
                            .uri("/search_all_leagues.php?l=" + name)
                            .retrieve()
                            .bodyToMono(League[][].class)
                            .block()[0][0])
                    .stream()
                    .filter(l -> l.getStrSport().equals("Soccer"))
                    .findFirst();

        } catch (NullPointerException e) {
            leagueByName = Optional.ofNullable(
                    webClient.get()
                            .uri("/search_all_leagues.php?l=" + name)
                            .retrieve()
                            .bodyToMono(League[].class)
                            .block()[0]);
        }

        return leagueByName;
    }

    @Override
    public League findLeagueById(String id) {

        logger.info("Getting league by id: [" + id + "]");

        return webClient.get()
                .uri("/lookupleague.php?id=" + id)
                .retrieve()
                .bodyToMono(Leagues.class)
                .block()
                .getLeagues()
                .get(0);
    }

}
