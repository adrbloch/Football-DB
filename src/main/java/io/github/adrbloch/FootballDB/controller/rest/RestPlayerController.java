package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.player.Player;
import io.github.adrbloch.FootballDB.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/api/players")
class RestPlayerController {

    private final PlayerService playerService;

    @Autowired
    RestPlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    Optional<List<Player>> getPlayersByTeamAndName(
            @RequestParam(required = false, name = "team") String teamName,
            @RequestParam("name") String playerName) {

        if (teamName == null)
            return playerService.findPlayersByName(playerName);
        else
            return playerService.findPlayersByTeamAndName(teamName, playerName);

    }

    @GetMapping(params = "id")
    Player getPlayerById(@RequestParam String id) {

        return playerService.findPlayerById(id);
    }


}
