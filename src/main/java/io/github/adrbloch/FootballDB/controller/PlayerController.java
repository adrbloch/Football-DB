package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.honor.Honor;
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

            playerService
                    .findPlayersByName(name)
                    .ifPresentOrElse(p -> model.addAttribute("players", p),
                            () -> model.addAttribute("players", null));

        return "results/playerResults";
    }

    @GetMapping(value = "/results", params = {"team", "name"})
    public String viewPlayersByTeamAndName(
            @RequestParam("team") String team,
            @RequestParam("name") String name,
            Model model) {

            playerService
                    .findPlayersByTeamAndName(team, name)
                    .ifPresentOrElse(p -> model.addAttribute("players", p),
                            () -> model.addAttribute("players", null));

        return "results/playerResults";
    }

    @GetMapping("/{id}")
    public String viewPlayerDetails(@PathVariable("id") String id, Model model) {

        model.addAttribute("player", playerService.findPlayerById(id));

        contractService
                .findContractsByPlayerId(id)
                .ifPresentOrElse(c -> model.addAttribute("contract", c),
                        () -> model.addAttribute("contract", null));


        formerTeamService
                .findFormerTeamsByPlayerId(id)
                .ifPresentOrElse(ft -> model.addAttribute("formerTeams", ft),
                        () -> model.addAttribute("formerTeams", null));


        List<Honor> honorList = honorService.findHonorsByPlayerId(id);

        if (honorList != null) {
            List<Honor> distinctHonorList = convertHonorsToDistinctList(honorList);
            model.addAttribute("honors", distinctHonorList);

        } else
            model.addAttribute("honors", null);

        return "data/player";
    }

    private List<Honor> convertHonorsToDistinctList(List<Honor> honorList) {

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
        }
        return distinctHonorList;
    }
}