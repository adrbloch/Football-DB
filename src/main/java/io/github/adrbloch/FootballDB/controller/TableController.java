package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.league.League;
import io.github.adrbloch.FootballDB.service.LeagueService;
import io.github.adrbloch.FootballDB.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Year;

@Controller
@RequestMapping("/table")
public class TableController {

    private final TableService tableService;
    private final LeagueService leagueService;

    @Autowired
    public TableController(TableService tableService, LeagueService leagueService) {
        this.tableService = tableService;
        this.leagueService = leagueService;
    }

    @GetMapping("/search")
    public String searchTables(Model model) {

        model.addAttribute("currentYear", Year.now().getValue());
        model.addAttribute("league", new League());

        return "search/searchTables";
    }

    @PostMapping("/results/byLeagueAndSeason")
    public String viewTablesByLeagueAndSeason(
            @ModelAttribute("league") League league,
            @ModelAttribute("season") String season,
            Model model) {

        if (league.getStrLeague().isEmpty() || season.isEmpty()) {
            model.addAttribute("leagues", null);
        } else {
            model.addAttribute("leagues", leagueService
                    .findLeagueByName(league.getStrLeague())
                    .block()[0][0]);
            model.addAttribute("season", season);
        }

        return "results/tableResults";
    }

    @GetMapping
    public String viewTableDetails(
            @RequestParam("leagueId") String leagueId,
            @RequestParam("season") String season,
            Model model) {

        model.addAttribute("table", tableService
                .findTableByLeagueIdAndSeason(leagueId, season)
                .block()
                .getTable());

        model.addAttribute("league", leagueService
                .findLeagueById(leagueId)
                .block()
                .getLeagues()
                .get(0));

        model.addAttribute("season", season);

        return "data/table";
    }

}

