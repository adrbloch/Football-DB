package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.table.TableTeam;
import io.github.adrbloch.FootballDB.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/api/table")
public class RestTableController {

    private final TableService tableService;

    @Autowired
    public RestTableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public Optional<List<TableTeam>> getTableByLeagueIdAndSeason(@RequestParam String leagueId, @RequestParam String season) {
        return tableService.findTableByLeagueIdAndSeason(leagueId, season);
    }
}
