package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.league.League;
import io.github.adrbloch.FootballDB.model.team.Team;
import io.github.adrbloch.FootballDB.service.LeagueService;
import io.github.adrbloch.FootballDB.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;
    private final LeagueService leagueService;

    @Autowired
    public TeamController(TeamService teamService, LeagueService leagueService) {
        this.teamService = teamService;
        this.leagueService = leagueService;
    }

    @GetMapping("/search")
    public String searchTeams(Model model) {
        model.addAttribute("team", new Team());
        return "search/searchTeams";
    }

    @GetMapping(value = "/results", params = "country")
    public String viewTeamsByCountry(@RequestParam("country") String country, Model model) {

        if (country.isEmpty()) {
            model.addAttribute("teams", null);
        } else {
            model.addAttribute("teams", teamService
                    .findTeamsByCountry(country)
                    .block()
                    .getTeams());
        }
        return "results/teamResults";
    }

    @GetMapping(value = "/results", params = "league")
    public String viewTeamsByLeague(@RequestParam("league") String league, Model model) {

        if (league.isEmpty()) {
            model.addAttribute("teams", null);
        } else {
            model.addAttribute("teams", teamService
                    .findTeamsByLeague(league)
                    .block()
                    .getTeams());
        }
        return "results/teamResults";
    }

    @GetMapping(value = "/results", params = "name")
    public String viewTeamByName(@RequestParam("name") String name, Model model) {

        if (name.isEmpty()) {
            model.addAttribute("teams", null);
        } else {
            model.addAttribute("teams", teamService
                    .findTeamsByName(name)
                    .block()
                    .getTeams());
        }
        return "results/teamResults";
    }

    @GetMapping("/{id}")
    public String viewTeamDetails(@PathVariable("id") String id, Model model){

        Team team = teamService
                .findTeamById(id)
                .block()
                .getTeams()
                .get(0);

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
        if (idLeague != null){
            League leagueById = leagueService
                    .findLeagueById(idLeague)
                    .block()
                    .getLeagues()
                    .get(0);

            leagueList.add(leagueById);
        }
    }

}
