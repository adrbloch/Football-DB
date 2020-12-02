package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.table.TableTeam;

import java.util.List;
import java.util.Optional;

public interface TableService {

    Optional<List<TableTeam>> findTableByLeagueIdAndSeason(String leagueId, String season);
}
