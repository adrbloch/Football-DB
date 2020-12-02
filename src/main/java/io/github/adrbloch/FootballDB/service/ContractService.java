package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.contract.Contract;

import java.util.Optional;

public interface ContractService {

    Optional<Contract> findContractsByPlayerId(String playerId);

}
