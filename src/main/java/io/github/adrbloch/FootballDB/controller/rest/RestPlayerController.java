package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.player.Players;
import io.github.adrbloch.FootballDB.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("rest//api/players")
public class RestPlayerController {

    private final PlayerService playerService;

    @Autowired
    public RestPlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public Mono<Players> getPlayersByTeamAndName(
            @RequestParam(required = false, name = "team") String teamName,
            @RequestParam("name") String playerName) {

        if (teamName == null)
            return playerService.findPlayersByName(playerName);
        else
            return playerService.findPlayersByTeamAndName(teamName, playerName);

    }


}
