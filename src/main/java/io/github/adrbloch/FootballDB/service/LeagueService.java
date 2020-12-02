package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.league.League;

import java.util.List;
import java.util.Optional;

public interface LeagueService {

    Optional<List<League>> findLeaguesByCountry(String country);

    Optional<League> findLeagueByName(String name);

    League findLeagueById(String id);
}
