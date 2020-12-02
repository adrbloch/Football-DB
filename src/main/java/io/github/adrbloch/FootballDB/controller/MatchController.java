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
public class MatchController {
    private final MatchService matchService;
    private final TeamService teamService;

    @Autowired
    public MatchController(MatchService matchService, TeamService teamService) {
        this.matchService = matchService;
        this.teamService = teamService;
    }

    @GetMapping("/search")
    public String searchMatches(Model model) {

        model.addAttribute("currentYear", Year.now().getValue());

        return "search/searchMatches";
    }

    @GetMapping(value = "/results", params = {"homeTeam", "awayTeam"})
    public String viewMatchesByTeams(
            @RequestParam("homeTeam") String homeTeam,
            @RequestParam("awayTeam") String awayTeam,
            Model model) {

        if (homeTeam.isEmpty() || awayTeam.isEmpty()) {
            model.addAttribute("matches", null);

        } else {
            matchService
                    .findMatchesByTeams(homeTeam, awayTeam)
                    .ifPresentOrElse(m -> model.addAttribute("matches", m),
                            () -> model.addAttribute("matches", null));
        }

        return "results/matchResults";
    }

    @GetMapping(value = "/results", params = {"homeTeam", "awayTeam", "season"})
    public String viewMatchesByTeamsAndSeason(
            @RequestParam("homeTeam") String homeTeam,
            @RequestParam("awayTeam") String awayTeam,
            @RequestParam("season") String season,
            Model model) {

        if (homeTeam.isEmpty() || awayTeam.isEmpty()) {
            model.addAttribute("matches", null);

        } else {
            matchService
                    .findMatchesByTeamsAndSeason(homeTeam, awayTeam, season)
                    .ifPresentOrElse(m -> model.addAttribute("matches", m),
                            () -> model.addAttribute("matches", null));
        }
        return "results/matchResults";
    }


    @GetMapping("/{id}")
    public String viewMatchDetails(@PathVariable("id") String id, Model model) {

        Match match = matchService.findMatchById(id);

        model.addAttribute("match", match);

        String homeGoalDetails = match.getStrHomeGoalDetails();
        List<String> homeGoalList = convertGoalDetailsToListReadyToView(homeGoalDetails);
        model.addAttribute("homeGoalList", homeGoalList);

        String awayGoalDetails = match.getStrAwayGoalDetails();
        List<String> awayGoalList = convertGoalDetailsToListReadyToView(awayGoalDetails);
        model.addAttribute("awayGoalList", awayGoalList);

        teamService
                .findTeamById(match.getIdHomeTeam())
                .ifPresentOrElse(t -> model.addAttribute("homeTeam", t),
                        () -> model.addAttribute("homeTeam", null));

        teamService
                .findTeamById(match.getIdAwayTeam())
                .ifPresentOrElse(t -> model.addAttribute("awayTeam", t),
                        () -> model.addAttribute("awayTeam", null));

        return "data/match";
    }

    private List<String> convertGoalDetailsToListReadyToView(String goalDetails) {
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
