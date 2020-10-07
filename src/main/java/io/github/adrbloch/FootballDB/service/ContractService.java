package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.contract.Contracts;
import reactor.core.publisher.Mono;

public interface ContractService {

    Mono<Contracts> findContractsByPlayerId(String playerId);

}
