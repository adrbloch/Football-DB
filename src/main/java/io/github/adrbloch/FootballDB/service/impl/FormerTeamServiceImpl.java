package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.formerTeam.FormerTeam;
import io.github.adrbloch.FootballDB.model.formerTeam.FormerTeams;
import io.github.adrbloch.FootballDB.service.FormerTeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormerTeamServiceImpl implements FormerTeamService {

    private static final Logger logger = LoggerFactory.getLogger(FormerTeamServiceImpl.class);
    private final WebClient webClient;

    @Autowired
    public FormerTeamServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<List<FormerTeam>> findFormerTeamsByPlayerId(String playerId) {

        logger.info("Getting former teams by player id: [" + playerId + "]");

        return Optional.ofNullable(
                webClient.get()
                        .uri("/lookupformerteams.php?id=" + playerId)
                        .retrieve()
                        .bodyToMono(FormerTeams.class)
                        .block()
                        .getFormerTeams())
                .map(list -> list
                        .stream()
                        .filter(p -> p.getStrSport().equals(("Soccer")))
                        .sorted(Comparator
                                .comparing(FormerTeam::getStrJoined))
                        .collect(Collectors.toList()));
    }
}
