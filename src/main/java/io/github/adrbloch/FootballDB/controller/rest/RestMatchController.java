package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.match.Match;
import io.github.adrbloch.FootballDB.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/api/matches")
public class RestMatchController {

    private final MatchService matchService;

    @Autowired
    RestMatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping(params = {"homeTeam", "awayTeam"})
    Optional<List<Match>> getMatchesByTeams(
            @RequestParam("homeTeam") String homeTeam,
            @RequestParam("awayTeam") String awayTeam) {

        return matchService.findMatchesByTeams(homeTeam, awayTeam);
    }

    @GetMapping(params = {"homeTeam", "awayTeam", "season"})
    Optional<List<Match>> getMatchesByTeamsAndSeason(
            @RequestParam("homeTeam") String homeTeam,
            @RequestParam("awayTeam") String awayTeam,
            @RequestParam("season") String season) {

        return matchService.findMatchesByTeamsAndSeason(homeTeam, awayTeam, season);
    }

    @GetMapping(params = "id")
    Match getMatchById(@RequestParam String id) {
        return matchService.findMatchById(id);
    }

}