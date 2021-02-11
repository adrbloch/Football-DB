package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.contract.Contract;
import io.github.adrbloch.FootballDB.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("rest/api/contracts")
class RestContractController {

    private final ContractService contractService;

    @Autowired
    RestContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    Optional<Contract> getContractsByPlayerId(@RequestParam String playerId) {
        return contractService.findContractsByPlayerId(playerId);

    }
}
