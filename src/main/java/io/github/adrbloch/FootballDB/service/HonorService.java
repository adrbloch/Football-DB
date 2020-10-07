package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.honor.Honors;
import reactor.core.publisher.Mono;

public interface HonorService {

    Mono<Honors> findHonorsByPlayerId(String playerId);
}
