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

        model.addAttribute("match", new Match());
        model.addAttribute("currentYear", Year.now().getValue());

        return "search/searchMatches";
    }

    @PostMapping("/results/byTeams")
    public String viewMatchesByTeams(@ModelAttribute("match") Match match, Model model) {

        if (match.getStrHomeTeam().isEmpty() || match.getStrAwayTeam().isEmpty()) {
            model.addAttribute("matches", null);
        } else {
            model.addAttribute("matches", matchService
                    .findMatchesByTeams(
                            match.getStrHomeTeam(),
                            match.getStrAwayTeam())
                    .block()
                    .getMatches()
                    .stream()
                    .sorted(Comparator
                            .comparing(Match::getDateEvent)
                            .reversed())
                    .collect(Collectors.toList()));
        }


        return "results/matchResults";
    }

    @PostMapping("/results/byTeamsAndSeason")
    public String viewMatchesByTeamsAndSeason(@ModelAttribute("match") Match match, Model model) {

        if (match.getStrHomeTeam().isEmpty() || match.getStrAwayTeam().isEmpty()) {
            model.addAttribute("matches", null);
        } else {
            model.addAttribute("matches", matchService
                    .findMatchesByTeamsAndSeason(
                            match.getStrHomeTeam(),
                            match.getStrAwayTeam(),
                            match.getStrSeason())
                    .block()
                    .getMatches()
                    .stream()
                    .sorted(Comparator
                            .comparing(Match::getDateEvent)
                            .reversed())
                    .collect(Collectors.toList()));
        }

        return "results/matchResults";
    }


    @GetMapping("/{id}")
    public String viewMatchDetails(@PathVariable("id") String id, Model model) {

        Match match = matchService
                .findMatchById(id)
                .block()
                .getMatchById()
                .get(0);

        model.addAttribute("match", match);


        String homeGoalDetails = match.getStrHomeGoalDetails();
        List<String> homeGoalList = new ArrayList<>();

        if (homeGoalDetails != null) {

            homeGoalDetails = homeGoalDetails.replace("penalty", "(penalty)");

            homeGoalList = Arrays.stream(
                    homeGoalDetails.split(";"))
                    .collect(Collectors.toList());

            Collections.reverse(homeGoalList);
        }
        model.addAttribute("homeGoalList", homeGoalList);


        String awayGoalDetails = match.getStrAwayGoalDetails();
        List<String> awayGoalList = new ArrayList<>();

        if (awayGoalDetails != null) {

            awayGoalDetails = awayGoalDetails.replace("penalty", "(penalty)");

            awayGoalList = Arrays.stream(
                    awayGoalDetails.split(";"))
                    .collect(Collectors.toList());

            Collections.reverse(awayGoalList);
        }
        model.addAttribute("awayGoalList", awayGoalList);


        model.addAttribute("homeTeam",
                teamService
                        .findTeamById(match.getIdHomeTeam())
                        .block()
                        .getTeams()
                        .get(0));

        model.addAttribute("awayTeam",
                teamService
                        .findTeamById(match.getIdAwayTeam())
                        .block()
                        .getTeams()
                        .get(0));


        return "data/match";
    }
}
