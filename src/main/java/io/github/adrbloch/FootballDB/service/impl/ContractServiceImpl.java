package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.contract.Contracts;
import io.github.adrbloch.FootballDB.model.event.Events;
import io.github.adrbloch.FootballDB.model.player.Players;
import io.github.adrbloch.FootballDB.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ContractServiceImpl implements ContractService {

    private static final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public ContractServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Contracts> findContractsByPlayerId(String playerId) {
        logger.info("Getting contrats by player id: [" + playerId + "]");

        return webClient.get()
                .uri("/lookupcontracts.php?id=" + playerId)
                .retrieve()
                .bodyToMono(Contracts.class);
    }
}
