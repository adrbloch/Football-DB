package io.github.adrbloch.FootballDB.model.league;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Leagues {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "leagues")
    private List<League> leagues = null;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "countrys")
    private List<League> leaguesByCountry = null;

}