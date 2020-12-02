package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.formerTeam.FormerTeam;
import io.github.adrbloch.FootballDB.service.FormerTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/api/former-teams")
public class RestFormerTeamController {

    private final FormerTeamService formerTeamService;

    @Autowired
    public RestFormerTeamController(FormerTeamService formerTeamService) {
        this.formerTeamService = formerTeamService;
    }

    @GetMapping
    public Optional<List<FormerTeam>> getFormerTeamsByPlayerId(@RequestParam String playerId) {
        return formerTeamService.findFormerTeamsByPlayerId(playerId);

    }
}
