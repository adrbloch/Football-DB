package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.league.League;
import io.github.adrbloch.FootballDB.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/api/leagues")
class RestLeagueController {

    private final LeagueService leagueService;

    @Autowired
    RestLeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping(params = "country")
    Optional<List<League>> getLeaguesByCountry(@RequestParam String country) {
        return leagueService.findLeaguesByCountry(country);
    }

    @GetMapping(params = "id")
    League getLeagueById(@RequestParam String id) {
        return leagueService.findLeagueById(id);
    }

    @GetMapping(params = "name")
    Optional<League> getLeagueByName(String name) {
        return leagueService.findLeagueByName(name);
    }
}
