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
import org.springframework.web.bind.annotation.*;

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
                            FormerTeamService formerTeamService, HonorService honorService) {

        this.playerService = playerService;
        this.contractService = contractService;
        this.formerTeamService = formerTeamService;
        this.honorService = honorService;
    }

    @GetMapping("/search")
    public String searchPlayers(Model model) {
        model.addAttribute("player", new Player());
        return "search/searchPlayers";
    }

    @PostMapping("/results/byName")
    public String viewPlayersByName(@ModelAttribute("player") Player player, Model model) {

        String playerName = player.getStrPlayer();

        if (playerName.isEmpty()) {
            model.addAttribute("players", null);

        } else {
            model.addAttribute("players",
                    playerService
                            .findPlayersByName(playerName)
                            .block()
                            .getPlayers()
                            .stream()
                            .filter(p -> p.getStrSport().equals("Soccer"))
                            .collect(Collectors.toList()));
        }

        return "results/playerResults";
    }

    @PostMapping("/results/byTeamAndName")
    public String viewPlayersByTeamAndName(@ModelAttribute("player") Player player, Model model) {

        String playerName = player.getStrPlayer();
        String playerTeam = player.getStrTeam();

        if (playerName.isEmpty() && playerTeam.isEmpty()) {
            model.addAttribute("players", null);

        } else {
            model.addAttribute("players",
                    playerService
                            .findPlayersByTeamAndName(player.getStrTeam(), player.getStrPlayer())
                            .block()
                            .getPlayers()
                            .stream()
                            .filter(p -> p.getStrSport().equals("Soccer"))
                            .collect(Collectors.toList()));
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

        model.addAttribute("contract",
                contractService
                        .findContractsByPlayerId(id)
                        .block()
                        .getContracts()
                        .get(0));

        model.addAttribute("formerTeams",
                formerTeamService
                        .findFormerTeamsByPlayerId(id)
                        .block().getFormerTeams()
                        .stream()
                        .sorted(Comparator
                                .comparing(FormerTeam::getStrJoined))
                        .collect(Collectors.toList()));


        List<Honor> honorList = honorService
                .findHonorsByPlayerId(id)
                .block()
                .getHonors()
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

        return "data/player";
    }
}