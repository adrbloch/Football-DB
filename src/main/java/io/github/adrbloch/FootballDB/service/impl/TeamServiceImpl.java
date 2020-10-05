package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.team.Teams;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TeamServiceImpl implements TeamService {

    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public TeamServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public Mono<Teams> findTeamsByName(String teamName) {
        logger.info("Getting team by name: [" + teamName + "]");

        return webClient.get()
                .uri("/searchteams.php?t=" + teamName)
                .retrieve()
                .bodyToMono(Teams.class);
    }

}
