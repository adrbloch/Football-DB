package io.github.adrbloch.FootballDB.model.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Players {

    @JsonProperty("player")
    private List<Player> players = null;

}