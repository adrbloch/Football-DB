package io.github.adrbloch.FootballDB.controller.rest;

import io.github.adrbloch.FootballDB.model.contract.Contracts;
import io.github.adrbloch.FootballDB.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("rest/api/contracts")
public class RestContractController {

    private final ContractService contractService;

    @Autowired
    public RestContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public Mono<Contracts> getContractsByPlayerId(@RequestParam String playerId) {
        return contractService.findContractsByPlayerId(playerId);

    }
}
