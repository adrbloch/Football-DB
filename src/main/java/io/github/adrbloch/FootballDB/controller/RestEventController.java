package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.model.event.Events;
import io.github.adrbloch.FootballDB.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/events")
public class RestEventController {

    private final EventService eventService;

    @Autowired
    public RestEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(params = {"name", "season"})
    public Mono<Events> getEventsByNameAndSeason(
            @RequestParam("name") String eventName,
            @RequestParam(name = "season", required = false) String season) {

        if (season == null)
            return eventService.findEventsByName(eventName);
        else
            return eventService.findEventByNameAndSeason(eventName, season);
    }

    @GetMapping(params = "id")
    public Mono<Events> getEventById(@RequestParam String id) {
            return eventService.findEventById(id);
    }

    @GetMapping("/next5")
    public Mono<Events> getNext5EventsByTeamId(@RequestParam String teamId) {
        return eventService.findNext5EventsByTeamId(teamId);
    }

    @GetMapping("/next15")
    public Mono<Events> getNext15EventsByLeagueId(@RequestParam String leagueId) {
        return eventService.findNext15EventsByLeagueId(leagueId);
    }

    @GetMapping("/last5")
    public Mono<Events> getLast5EventsByTeamId(@RequestParam String teamId) {
        return eventService.findLast5EventsByTeamId(teamId);
    }

    @GetMapping("/last15")
    public Mono<Events> getLast15EventsByLeagueId(@RequestParam String leagueId) {
        return eventService.findLast15EventsByLeagueId(leagueId);
    }

    @GetMapping(params = {"leagueId", "round", "season"})
    public Mono<Events> getEventsOfRoundByLeagueIdAndRoundAndSeason(@RequestParam String leagueId, @RequestParam String round, @RequestParam String season) {
        return eventService.findEventsOfRoundByLeagueIdAndRoundAndSeason(leagueId, round, season);
    }

}