package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.table.Table;
import io.github.adrbloch.FootballDB.model.table.TableTeam;
import io.github.adrbloch.FootballDB.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {

    private static final Logger logger = LoggerFactory.getLogger(TableServiceImpl.class);
    private final WebClient webClient;

    @Autowired
    public TableServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<List<TableTeam>> findTableByLeagueIdAndSeason(String leagueId, String season) {

        logger.info("Getting table by league id: [{}] and season: [{}]", leagueId, season);

        Optional<List<TableTeam>> table;

        try {
            table = Optional.ofNullable(
                    webClient.get()
                            .uri("/lookuptable.php?l=" + leagueId + "&s=" + season)
                            .retrieve()
                            .bodyToMono(Table.class)
                            .block()
                            .getTable());

        } catch (UnsupportedMediaTypeException e) {
           table = Optional.empty();
        }

        return table;
    }
}
