package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.team.Team;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/api/teams")
public class RestTeamController {

    private final TeamService teamService;

    @Autowired
    public RestTeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(params = "name")
    public Optional<List<Team>> getTeamsByName(@RequestParam String name) {
        return teamService.findTeamsByName(name);
    }

    @GetMapping(params = "league")
    public Optional<List<Team>> getTeamsByLeague(@RequestParam String league) {
        return teamService.findTeamsByLeague(league);
    }

    @GetMapping(params = "country")
    public Optional<List<Team>> getTeamsByCountry(@RequestParam String country) {
        return teamService.findTeamsByCountry(country);
    }

    @GetMapping(params = "id")
    public Team getTeamById(@RequestParam String id) {
        return teamService.findTeamById(id);
    }
}
