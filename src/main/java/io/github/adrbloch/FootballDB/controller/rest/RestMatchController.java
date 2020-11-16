package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.match.Matches;
import io.github.adrbloch.FootballDB.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("rest/api/matches")
public class RestMatchController {

    private final MatchService matchService;

    @Autowired
    public RestMatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping(params = {"homeTeam", "awayTeam"})
    public Mono<Matches> getMatchesByTeams(
            @RequestParam("homeTeam") String homeTeam,
            @RequestParam("awayTeam") String awayTeam) {

        return matchService.findMatchesByTeams(homeTeam, awayTeam);
    }

    @GetMapping(params = {"homeTeam", "awayTeam", "season"})
    public Mono<Matches> getMatchesByTeamsAndSeason(
            @RequestParam("homeTeam") String homeTeam,
            @RequestParam("awayTeam") String awayTeam,
            @RequestParam("season") String season) {

        return matchService.findMatchesByTeamsAndSeason(homeTeam, awayTeam, season);
    }

    @GetMapping(params = "id")
    public Mono<Matches> getMatchById(@RequestParam String id) {
        return matchService.findMatchById(id);
    }

    @GetMapping("/next5")
    public Mono<Matches> getNext5MatchesByTeamId(@RequestParam String teamId) {
        return matchService.findNext5MatchesByTeamId(teamId);
    }

    @GetMapping("/next15")
    public Mono<Matches> getNext15MatchesByLeagueId(@RequestParam String leagueId) {
        return matchService.findNext15MatchesByLeagueId(leagueId);
    }

    @GetMapping("/last5")
    public Mono<Matches> getLast5MatchesByTeamId(@RequestParam String teamId) {
        return matchService.findLast5MatchesByTeamId(teamId);
    }

    @GetMapping("/last15")
    public Mono<Matches> getLast15MatchesByLeagueId(@RequestParam String leagueId) {
        return matchService.findLast15MatchesByLeagueId(leagueId);
    }

    @GetMapping(params = {"leagueId", "round", "season"})
    public Mono<Matches> getMatchesOfRoundByLeagueIdAndRoundAndSeason(@RequestParam String leagueId,
                                                                      @RequestParam String round,
                                                                      @RequestParam String season) {
        return matchService.findMatchesOfRoundByLeagueIdAndRoundAndSeason(leagueId, round, season);
    }

}