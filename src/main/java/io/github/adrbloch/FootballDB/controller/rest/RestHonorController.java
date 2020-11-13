package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.honor.Honors;
import io.github.adrbloch.FootballDB.service.HonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("rest/api/honors")
public class RestHonorController {

    private final HonorService honorService;

    @Autowired
    public RestHonorController(HonorService honorService) {
        this.honorService = honorService;
    }

    @GetMapping
    public Mono<Honors> getLeaguesByCountry(@RequestParam String playerId) {
        return honorService.findHonorsByPlayerId(playerId);
    }
}