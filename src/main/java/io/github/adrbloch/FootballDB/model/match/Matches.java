package io.github.adrbloch.FootballDB.model.match;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Matches {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("event")
    private List<Match> matches = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("events")
    private List<Match> matchById = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("results")
    private List<Match> lastMatches = new ArrayList<>();

}