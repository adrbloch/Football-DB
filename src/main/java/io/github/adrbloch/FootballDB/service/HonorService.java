package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.honor.Honor;

import java.util.List;

public interface HonorService {

    List<Honor> findHonorsByPlayerId(String playerId);
}
