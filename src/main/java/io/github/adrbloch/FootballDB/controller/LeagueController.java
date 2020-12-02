package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.league.League;
import io.github.adrbloch.FootballDB.service.LeagueService;
import io.github.adrbloch.FootballDB.service.TableService;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/league")
public class LeagueController {

    private final LeagueService leagueService;
    private final TeamService teamService;
    private final TableService tableService;

    @Autowired
    public LeagueController(LeagueService leagueService, TeamService teamService, TableService tableService) {
        this.leagueService = leagueService;
        this.teamService = teamService;
        this.tableService = tableService;
    }


    @GetMapping("/search")
    public String searchLeagues() {

        return "search/searchLeagues";
    }

    @GetMapping(value = "/results", params = "country")
    public String viewLeaguesByCountry(@RequestParam("country") String country, Model model) {

        leagueService
                .findLeaguesByCountry(country)
                .ifPresentOrElse(league -> model.addAttribute("leagues", league),
                        () -> model.addAttribute("leagues", null));

        return "results/leagueResults";
    }

    @GetMapping(value = "/results", params = "name")
    public String viewLeaguesByName(@RequestParam("name") String name, Model model) {

        leagueService
                .findLeagueByName(name)
                .ifPresentOrElse(league -> model.addAttribute("leagues", league),
                        () -> model.addAttribute("leagues", null));

        return "results/leagueResults";
    }

    @GetMapping("/{id}")
    public String viewLeagueDetails(@PathVariable("id") String id, Model model) {

        League league = leagueService.findLeagueById(id);

        model.addAttribute("league", league);

        teamService
                .findTeamsByLeague(league.getStrLeague())
                .ifPresentOrElse(t -> model.addAttribute("teamsByLeague", t),
                        () -> model.addAttribute("teamsByLeague", null));
        tableService
                .findTableByLeagueIdAndSeason(league.getIdLeague(), league.getStrCurrentSeason())
                .ifPresentOrElse(table -> model.addAttribute("tableByLeague", table),
                        () -> model.addAttribute("tableByLeague", null));

        return "data/league";
    }
}
