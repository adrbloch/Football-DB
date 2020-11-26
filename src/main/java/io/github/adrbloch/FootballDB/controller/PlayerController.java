package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.formerTeam.FormerTeam;
import io.github.adrbloch.FootballDB.model.honor.Honor;
import io.github.adrbloch.FootballDB.model.player.Player;
import io.github.adrbloch.FootballDB.service.ContractService;
import io.github.adrbloch.FootballDB.service.FormerTeamService;
import io.github.adrbloch.FootballDB.service.HonorService;
import io.github.adrbloch.FootballDB.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    private final ContractService contractService;
    private final FormerTeamService formerTeamService;
    private final HonorService honorService;

    @Autowired
    public PlayerController(PlayerService playerService,
                            ContractService contractService,
                            FormerTeamService formerTeamService,
                            HonorService honorService) {

        this.playerService = playerService;
        this.contractService = contractService;
        this.formerTeamService = formerTeamService;
        this.honorService = honorService;
    }

    @GetMapping("/search")
    public String searchPlayers() {

        return "search/searchPlayers";
    }

    @GetMapping(value = "/results", params = "name")
    public String viewPlayersByName(@RequestParam("name") String name, Model model) {

        if (name.isEmpty()) {
            model.addAttribute("players", null);
        } else {

            try {
                model.addAttribute("players",
                        playerService
                                .findPlayersByName(name)
                                .block()
                                .getPlayers()
                                .stream()
                                .filter(p -> p.getStrSport().equals("Soccer"))
                                .collect(Collectors.toList()));

            } catch (NullPointerException e) {
                model.addAttribute("players", null);
            }
        }

        return "results/playerResults";
    }

    @GetMapping(value = "/results", params = {"team", "name"})
    public String viewPlayersByTeamAndName(
            @RequestParam("team") String team,
            @RequestParam("name") String name,
            Model model) {

        if (team.isEmpty() && name.isEmpty()) {
            model.addAttribute("players", null);
        } else {

            try {
                List<Player> playerList = playerService
                        .findPlayersByTeamAndName(team, name)
                        .block()
                        .getPlayers()
                        .stream()
                        .filter(p -> p.getStrSport().equals("Soccer"))
                        .collect(Collectors.toList());

                if (playerList.size() == 0)
                    model.addAttribute("players", null);
                else
                    model.addAttribute("players", playerList);

            } catch (NullPointerException e) {
                model.addAttribute("players", null);
            }
        }

        return "results/playerResults";
    }

    @GetMapping("/{id}")
    public String viewPlayerDetails(@PathVariable("id") String id, Model model) {

        model.addAttribute("player",
                playerService
                        .findPlayerById(id)
                        .block()
                        .getPlayersById()
                        .get(0));

        try {
            model.addAttribute("contract",
                    contractService
                            .findContractsByPlayerId(id)
                            .block()
                            .getContracts()
                            .get(0));
        } catch (NullPointerException e) {
            model.addAttribute("contract", null);
        }


        try {
            model.addAttribute("formerTeams",
                    formerTeamService
                            .findFormerTeamsByPlayerId(id)
                            .block().getFormerTeams()
                            .stream()
                            .sorted(Comparator
                                    .comparing(FormerTeam::getStrJoined))
                            .collect(Collectors.toList()));
        } catch (NullPointerException e) {
            model.addAttribute("formerTeams", null);
        }


        List<Honor> honorList = honorService
                .findHonorsByPlayerId(id)
                .block()
                .getHonors();

        if (honorList != null) {
            honorList
                    .stream()
                    .sorted(Comparator
                            .comparing(Honor::getStrSeason))
                    .collect(Collectors.toList());

            List<Honor> distinctHonorList = new ArrayList<>();

            for (Honor honor : honorList) {
                Honor elementByHonorName = distinctHonorList
                        .stream()
                        .filter(h -> h.getStrHonor()
                                .equals(honor.getStrHonor()))
                        .findFirst()
                        .orElse(null);

                if (elementByHonorName == null)
                    distinctHonorList.add(honor);
                else
                    elementByHonorName.setStrSeason(elementByHonorName.getStrSeason() + ", " + honor.getStrSeason());

                model.addAttribute("honors", distinctHonorList);
            }
        } else
            model.addAttribute("honors", null);

        return "data/player";
    }
}