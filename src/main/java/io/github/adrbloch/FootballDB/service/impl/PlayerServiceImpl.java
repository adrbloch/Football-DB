package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.player.Players;
import io.github.adrbloch.FootballDB.model.team.Teams;
import io.github.adrbloch.FootballDB.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public PlayerServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Players> findPlayersByName(String playerName) {
        logger.info("Getting players by name: [" + playerName + "]");

        return webClient.get()
                .uri("/searchplayers.php?p=" + playerName)
                .retrieve()
                .bodyToMono(Players.class);
    }

    @Override
    public Mono<Players> findPlayersByTeamAndName(String teamName, String playerName) {
        logger.info("Getting players by team: [{}] and name: [{}]", teamName, playerName);

        return webClient.get()
                .uri("/searchplayers.php?t=" + teamName + "&p=" + playerName)
                .retrieve()
                .bodyToMono(Players.class);
    }

    @Override
    public Mono<Players> findPlayerById(String id) {
        logger.info("Getting player by id: [" + id + "]");

        return webClient.get()
                .uri("/lookupplayer.php?id=" + id)
                .retrieve()
                .bodyToMono(Players.class);
    }
}
