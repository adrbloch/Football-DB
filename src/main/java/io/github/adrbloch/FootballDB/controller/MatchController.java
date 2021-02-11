package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.match.Match;
import io.github.adrbloch.FootballDB.service.MatchService;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/match")
class MatchController {
    private final MatchService matchService;
    private final TeamService teamService;

    @Autowired
    MatchController(MatchService matchService, TeamService teamService) {
        this.matchService = matchService;
        this.teamService = teamService;
    }

    @GetMapping("/search")
    String searchMatches(Model model) {

        model.addAttribute("currentYear", Year.now().getValue());

        return "search/searchMatches";
    }

    @GetMapping(value = "/results", params = {"homeTeam", "awayTeam"})
    String viewMatchesByTeams(
            @RequestParam("homeTeam") String homeTeam,
            @RequestParam("awayTeam") String awayTeam,
            Model model) {

        matchService
                .findMatchesByTeams(homeTeam, awayTeam)
                .ifPresentOrElse(m -> model.addAttribute("matches", m),
                        () -> model.addAttribute("matches", null));

        return "results/matchResults";
    }

    @GetMapping(value = "/results", params = {"homeTeam", "awayTeam", "season"})
    String viewMatchesByTeamsAndSeason(
            @RequestParam("homeTeam") String homeTeam,
            @RequestParam("awayTeam") String awayTeam,
            @RequestParam("season") String season,
            Model model) {

        matchService
                .findMatchesByTeamsAndSeason(homeTeam, awayTeam, season)
                .ifPresentOrElse(m -> model.addAttribute("matches", m),
                        () -> model.addAttribute("matches", null));

        return "results/matchResults";
    }


    @GetMapping("/{id}")
    String viewMatchDetails(@PathVariable("id") String id, Model model) {

        Match match = matchService.findMatchById(id);

        model.addAttribute("match", match);

        String homeGoalDetails = match.getStrHomeGoalDetails();
        List<String> homeGoalList = prepareGoalDetailsListToView(homeGoalDetails);
        model.addAttribute("homeGoalList", homeGoalList);

        String awayGoalDetails = match.getStrAwayGoalDetails();
        List<String> awayGoalList = prepareGoalDetailsListToView(awayGoalDetails);
        model.addAttribute("awayGoalList", awayGoalList);


        model.addAttribute("homeTeam",
                teamService
                        .findTeamById(match.getIdHomeTeam()));

        model.addAttribute("awayTeam",
                teamService
                        .findTeamById(match.getIdAwayTeam()));

        return "data/match";
    }

    private List<String> prepareGoalDetailsListToView(String goalDetails) {
        List<String> goalList = new ArrayList<>();

        if (goalDetails != null) {

            goalDetails = goalDetails.replace("penalty", "(penalty)");

            goalList = Arrays.stream(
                    goalDetails.split(";"))
                    .collect(Collectors.toList());

            Collections.reverse(goalList);
        }
        return goalList;
    }
}
