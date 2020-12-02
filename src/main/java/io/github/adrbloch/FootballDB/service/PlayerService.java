package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    Optional<List<Player>> findPlayersByName(String playerName);

    Optional<List<Player>> findPlayersByTeamAndName(String teamName, String playerName);

    Player findPlayerById(String id);
}
