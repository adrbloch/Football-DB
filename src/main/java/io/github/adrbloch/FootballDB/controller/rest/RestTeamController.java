package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.team.Team;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/api/teams")
class RestTeamController {

    private final TeamService teamService;

    @Autowired
    RestTeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(params = "name")
    Optional<List<Team>> getTeamsByName(@RequestParam String name) {
        return teamService.findTeamsByName(name);
    }

    @GetMapping(params = "league")
    Optional<List<Team>> getTeamsByLeague(@RequestParam String league) {
        return teamService.findTeamsByLeague(league);
    }

    @GetMapping(params = "country")
    Optional<List<Team>> getTeamsByCountry(@RequestParam String country) {
        return teamService.findTeamsByCountry(country);
    }

    @GetMapping(params = "id")
    Team getTeamById(@RequestParam String id) {
        return teamService.findTeamById(id);
    }
}
