package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.team.Teams;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("rest/api/teams")
public class RestTeamController {

    private final TeamService teamService;

    @Autowired
    public RestTeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(params = "name")
    public Mono<Teams> getTeamsByName(@RequestParam String name) {
        return teamService.findTeamsByName(name);
    }

    @GetMapping(params = "league")
    public Mono<Teams> getTeamsByLeague(@RequestParam String league) {
        return teamService.findTeamsByLeague(league);
    }

    @GetMapping(params = "country")
    public Mono<Teams> getTeamsByCountry(@RequestParam String country) {
        return teamService.findTeamsByCountry(country);
    }

    @GetMapping(params = "id")
    public Mono<Teams> getTeamById(@RequestParam String id) {
        return teamService.findTeamById(id);
    }
}
