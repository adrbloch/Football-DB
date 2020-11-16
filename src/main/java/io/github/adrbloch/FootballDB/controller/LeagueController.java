package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.league.League;
import io.github.adrbloch.FootballDB.model.table.TableTeam;
import io.github.adrbloch.FootballDB.service.LeagueService;
import io.github.adrbloch.FootballDB.service.TableService;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;

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


    @GetMapping("/search")
    public String searchLeagues() {

        return "search/searchLeagues";
    }

    @GetMapping(value = "/results", params = "country")
    public String viewLeaguesByCountry(@RequestParam("country") String country, Model model) {

        model.addAttribute("leagues", leagueService
                .findLeaguesByCountry(country)
                .block()
                .getLeaguesByCountry());

        return "results/leagueResults";
    }

    @GetMapping(value = "/results", params = "name")
    public String viewLeaguesByName(@RequestParam("name") String name, Model model) {

        try {
            model.addAttribute("leagues",
                    leagueService
                            .findLeagueByName(name)
                            .block()[0][0]);
        } catch (NullPointerException e) {
            model.addAttribute("leagues", null);
        }
        return "results/leagueResults";
    }

    @GetMapping("/{id}")
    public String viewLeagueDetails(@PathVariable("id") String id, Model model) {
        League league = leagueService
                .findLeagueById(id)
                .block()
                .getLeagues()
                .get(0);

        model.addAttribute("league", league);

        model.addAttribute("teamsByLeague",
                teamService
                        .findTeamsByLeague(league.getStrLeague())
                        .block()
                        .getTeams());


        List<TableTeam> tableByLeagueIdAndSeason;
        try {
            tableByLeagueIdAndSeason = tableService
                    .findTableByLeagueIdAndSeason(league.getIdLeague(), league.getStrCurrentSeason())
                    .block()
                    .getTable();
        } catch (UnsupportedMediaTypeException e) {
            tableByLeagueIdAndSeason = null;
        }

        model.addAttribute("tableByLeague", tableByLeagueIdAndSeason);

        return "data/league";
    }
}
