package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.team.Teams;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/team")
public class RestTeamController {

    private TeamService teamService;

    public RestTeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{name}")
    public Mono<Teams> getTeamByName(@PathVariable("name") String teamName) {
        return teamService.findTeamsByName(teamName);
    }

}
