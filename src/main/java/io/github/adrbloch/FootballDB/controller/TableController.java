package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.league.League;
import io.github.adrbloch.FootballDB.model.table.TableTeam;
import io.github.adrbloch.FootballDB.service.LeagueService;
import io.github.adrbloch.FootballDB.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/table")
class TableController {

    private final TableService tableService;
    private final LeagueService leagueService;

    @Autowired
    TableController(TableService tableService, LeagueService leagueService) {
        this.tableService = tableService;
        this.leagueService = leagueService;
    }

    @GetMapping("/search")
    String searchTables(Model model) {

        model.addAttribute("currentYear", Year.now().getValue());

        return "search/searchTables";
    }

    @GetMapping(value = "/results", params = {"league", "season"})
    String viewTablesByLeagueAndSeason(
            @RequestParam("league") String league,
            @RequestParam("season") String season,
            Model model) {

        Optional<League> leagueByName = leagueService
                .findLeagueByName(league);

        leagueByName
                .ifPresentOrElse(lg -> model.addAttribute("leagues", lg),
                        () -> model.addAttribute("leagues", null));
        try {
            if (leagueByName.isPresent()) {

                Optional<List<TableTeam>> tableByLeagueIdAndSeason = tableService
                        .findTableByLeagueIdAndSeason(leagueByName.get().getIdLeague(), season);

                if (tableByLeagueIdAndSeason.isEmpty())
                    model.addAttribute("leagues", null);
            }

        } catch (UnsupportedMediaTypeException e) {
            model.addAttribute("leagues", null);
        }

        model.addAttribute("season", season);

        return "results/tableResults";
    }

    @GetMapping
    String viewTableDetails(
            @RequestParam("leagueId") String leagueId,
            @RequestParam("season") String season,
            Model model) {

        tableService
                .findTableByLeagueIdAndSeason(leagueId, season)
                .ifPresentOrElse(t -> model.addAttribute("table", t),
                        () -> model.addAttribute("table", null));

        model.addAttribute("league", leagueService.findLeagueById(leagueId));
        model.addAttribute("season", season);

        return "data/table";
    }

}

