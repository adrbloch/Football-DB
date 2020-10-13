package io.github.adrbloch.FootballDB.model.match;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Matches {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("event")
    private List<Match> match = null;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("events")
    private List<Match> matches = null;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("results")
    private List<Match> lastMatches = null;

}