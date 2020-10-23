package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.league.League;
import io.github.adrbloch.FootballDB.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/league")
public class LeagueController {

    private final LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }


    @PostMapping("/results/byCountry")
    public String viewAllByCountry (@ModelAttribute("league") League league, Model model) {

        model.addAttribute("leagues", leagueService
                .findLeaguesByCountry(league.getStrCountry())
                .block()
                .getLeaguesByCountry());

        return "results/leagueResults";
    }

    @PostMapping("/results/byName")
    public String viewByName(@ModelAttribute("league") League league, Model model) {

        try {
            model.addAttribute("leagues", leagueService
                    .findLeagueByName(league.getStrLeague())
                    .block()[0][0]);
        } catch (NullPointerException e) {
            model.addAttribute("leagues", null);
        }
        return "results/leagueResults";
    }

    @GetMapping("/search")
    public String searchByName(Model model) {
        model.addAttribute("league", new League());
        return "search/searchLeagues";
    }
}
