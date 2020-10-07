package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.table.Table;
import reactor.core.publisher.Mono;

public interface TableService {
    Mono<Table> findTableByLeagueIdAndSeason(String leagueId, String season);
}
