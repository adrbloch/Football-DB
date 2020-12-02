package io.github.adrbloch.FootballDB.service;

import io.github.adrbloch.FootballDB.model.formerTeam.FormerTeam;

import java.util.List;
import java.util.Optional;

public interface FormerTeamService {

    Optional<List<FormerTeam>> findFormerTeamsByPlayerId(String playerId);
}
