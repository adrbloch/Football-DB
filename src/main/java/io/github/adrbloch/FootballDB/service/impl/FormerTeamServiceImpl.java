package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.formerTeam.FormerTeams;
import io.github.adrbloch.FootballDB.service.FormerTeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FormerTeamServiceImpl implements FormerTeamService {


    private static final Logger logger = LoggerFactory.getLogger(FormerTeamServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public FormerTeamServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<FormerTeams> findFormerTeamsByPlayerId(String playerId) {
        logger.info("Getting former teams by player id: [" + playerId + "]");

        return webClient.get()
                .uri("/lookupformerteams.php?id=" + playerId)
                .retrieve()
                .bodyToMono(FormerTeams.class);
    }
}
