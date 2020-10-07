package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.league.Leagues;
import io.github.adrbloch.FootballDB.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/leagues")
public class RestLeagueController {

    private final LeagueService leagueService;

    @Autowired
    public RestLeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping(params = "country")
    public Mono<Leagues> getLeaguesByCountry(@RequestParam String country) {
        return leagueService.findLeaguesByCountry(country);
    }

    @GetMapping(params = "id")
    public Mono<Leagues> getLeagueById(@RequestParam String id) {
        return leagueService.findLeagueById(id);
    }
}