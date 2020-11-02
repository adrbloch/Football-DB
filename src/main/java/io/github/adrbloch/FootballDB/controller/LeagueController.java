package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.league.League;
import io.github.adrbloch.FootballDB.model.table.Table;
import io.github.adrbloch.FootballDB.model.table.TableTeam;
import io.github.adrbloch.FootballDB.service.LeagueService;
import io.github.adrbloch.FootballDB.service.TableService;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


    @PostMapping("/results/byCountry")
    public String viewAllByCountry(@ModelAttribute("league") League league, Model model) {

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
    public String searchLeagues(Model model) {
        model.addAttribute("league", new League());
        return "search/searchLeagues";
    }

    @GetMapping("/{id}")
    public String viewLeagueDetails(@PathVariable("id") String id, Model model) {
        League league = leagueService
                .findLeagueById(id)
                .block()
                .getLeagues()
                .get(0);

        model.addAttribute("league", league);

        model.addAttribute("teamsByLeague", teamService.findTeamsByLeague(league
                .getStrLeague())
                .block()
                .getTeams());


        List<TableTeam> tableByLeagueIdAndSeason;
        try {
            tableByLeagueIdAndSeason = tableService.findTableByLeagueIdAndSeason(league.getIdLeague(),
                    league.getStrCurrentSeason()).block().getTable();
        } catch (UnsupportedMediaTypeException e) {
            tableByLeagueIdAndSeason = null;
        }

//        if (!tableByLeagueIdAndSeason.block().toString().) {
//            model.addAttribute("tableByLeague", tableByLeagueIdAndSeason);
//        } else  model.addAttribute("tableByLeague", new Table());

        model.addAttribute("tableByLeague", tableByLeagueIdAndSeason);

        return "data/league";
    }
}
