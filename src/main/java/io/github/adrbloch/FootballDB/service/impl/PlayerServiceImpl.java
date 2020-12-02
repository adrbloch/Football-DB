package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.player.Player;
import io.github.adrbloch.FootballDB.model.player.Players;
import io.github.adrbloch.FootballDB.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
    private final WebClient webClient;

    @Autowired
    public PlayerServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<List<Player>> findPlayersByName(String playerName) {

        logger.info("Getting players by name: [" + playerName + "]");

        return Optional.ofNullable(
                webClient.get()
                        .uri("/searchplayers.php?p=" + playerName)
                        .retrieve()
                        .bodyToMono(Players.class)
                        .block()
                        .getPlayers())
                        .map(list -> list
                                .stream()
                                .filter(p -> p.getStrSport().equals(("Soccer")))
                                .collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Player>> findPlayersByTeamAndName(String teamName, String playerName) {

        logger.info("Getting players by team: [{}] and name: [{}]", teamName, playerName);

        Optional<List<Player>> players = Optional.ofNullable(
                webClient.get()
                        .uri("/searchplayers.php?t=" + teamName + "&p=" + playerName)
                        .retrieve()
                        .bodyToMono(Players.class)
                        .block()
                        .getPlayers())
                .map(list -> list
                        .stream()
                        .filter(p -> p.getStrSport().equals(("Soccer")))
                        .collect(Collectors.toList()));

        if (players.isPresent() && players.get().size() == 0){
            players = Optional.empty();
        }

        return players;
    }

    @Override
    public Player findPlayerById(String id) {

        logger.info("Getting player by id: [" + id + "]");

        return webClient.get()
                .uri("/lookupplayer.php?id=" + id)
                .retrieve()
                .bodyToMono(Players.class)
                .block()
                .getPlayersById()
                .get(0);
    }
}
