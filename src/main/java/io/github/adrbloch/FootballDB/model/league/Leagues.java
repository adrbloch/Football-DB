package io.github.adrbloch.FootballDB.model.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Leagues {

    @JsonProperty("leagues")
    public List<League> leagues = null;

}