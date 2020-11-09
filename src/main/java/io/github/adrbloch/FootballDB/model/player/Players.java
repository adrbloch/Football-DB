package io.github.adrbloch.FootballDB.model.player;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Players {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("player")
    private List<Player> players = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("players")
    private List<Player> playersById = new ArrayList<>();

}