package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.league.League;
import io.github.adrbloch.FootballDB.model.team.Team;
import io.github.adrbloch.FootballDB.service.LeagueService;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/team")
class TeamController {

    private final TeamService teamService;
    private final LeagueService leagueService;

    @Autowired
    TeamController(TeamService teamService, LeagueService leagueService) {
        this.teamService = teamService;
        this.leagueService = leagueService;
    }

    @GetMapping("/search")
    String searchTeams(Model model) {

        model.addAttribute("team", new Team());

        return "search/searchTeams";
    }

    @GetMapping(value = "/results", params = "country")
    String viewTeamsByCountry(@RequestParam("country") String country, Model model) {

        teamService
                .findTeamsByCountry(country)
                .ifPresentOrElse(t -> model.addAttribute("teams", t),
                        () -> model.addAttribute("teams", null));

        return "results/teamResults";
    }

    @GetMapping(value = "/results", params = "league")
    String viewTeamsByLeague(@RequestParam("league") String league, Model model) {

        teamService
                .findTeamsByLeague(league)
                .ifPresentOrElse(t -> model.addAttribute("teams", t),
                        () -> model.addAttribute("teams", null));

        return "results/teamResults";
    }

    @GetMapping(value = "/results", params = "name")
    String viewTeamByName(@RequestParam("name") String name, Model model) {

        teamService
                .findTeamsByName(name)
                .ifPresentOrElse(t -> model.addAttribute("teams", t),
                        () -> model.addAttribute("teams", null));

        return "results/teamResults";
    }

    @GetMapping("/{id}")
    String viewTeamDetails(@PathVariable("id") String id, Model model) {

        Team team = teamService.findTeamById(id);

        model.addAttribute("team", team);

        List<League> leaguesByTeam = new ArrayList<>();
        addNotNullLeagueToList(team.getIdLeague(), leaguesByTeam);
        addNotNullLeagueToList(team.getIdLeague2(), leaguesByTeam);
        addNotNullLeagueToList(team.getIdLeague3(), leaguesByTeam);
        addNotNullLeagueToList(team.getIdLeague4(), leaguesByTeam);
        addNotNullLeagueToList(team.getIdLeague5(), leaguesByTeam);
        addNotNullLeagueToList(team.getIdLeague6(), leaguesByTeam);
        addNotNullLeagueToList(team.getIdLeague7(), leaguesByTeam);

        model.addAttribute("leaguesByTeam", leaguesByTeam);

        return "data/team";
    }


    private void addNotNullLeagueToList(String idLeague, List<League> leagueList) {

        if (idLeague != null)
            leagueList.add(leagueService
                    .findLeagueById(idLeague));
    }

}
