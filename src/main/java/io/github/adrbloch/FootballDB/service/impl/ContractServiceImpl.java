package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.contract.Contract;
import io.github.adrbloch.FootballDB.model.contract.Contracts;
import io.github.adrbloch.FootballDB.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private static final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);
    private final WebClient webClient;

    @Autowired
    public ContractServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<Contract> findContractsByPlayerId(String playerId) {

        logger.info("Getting contracts by player id: [" + playerId + "]");

        Optional<List<Contract>> contracts = Optional.ofNullable(
                webClient.get()
                        .uri("/lookupcontracts.php?id=" + playerId)
                        .retrieve()
                        .bodyToMono(Contracts.class)
                        .block()
                        .getContracts());

        Optional<Contract> contract = Optional.empty();

        if (contracts.isPresent())
            contract = Optional.ofNullable(contracts.get().get(0));

        return contract;
    }
}
