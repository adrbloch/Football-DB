package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.player.Players;
import io.github.adrbloch.FootballDB.model.table.Table;
import io.github.adrbloch.FootballDB.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TableServiceImpl implements TableService {

    private static final Logger logger = LoggerFactory.getLogger(TableServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public TableServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Table> findTableByLeagueIdAndSeason(String leagueId, String season) {
        logger.info("Getting table by league id: [{}] and season: [{}]", leagueId, season);

        return webClient.get()
                .uri("/lookuptable.php?l=" + leagueId + "&s=" + season)
                .retrieve()
                .bodyToMono(Table.class);
    }
}
