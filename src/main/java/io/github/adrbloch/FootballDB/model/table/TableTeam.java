package io.github.adrbloch.FootballDB.model.table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableTeam {

    @JsonProperty("name")
    private String name;
    @JsonProperty("teamid")
    private String teamid;
    @JsonProperty("played")
    private Integer played;
    @JsonProperty("goalsfor")
    private Integer goalsfor;
    @JsonProperty("goalsagainst")
    private Integer goalsagainst;
    @JsonProperty("goalsdifference")
    private Integer goalsdifference;
    @JsonProperty("win")
    private Integer win;
    @JsonProperty("draw")
    private Integer draw;
    @JsonProperty("loss")
    private Integer loss;
    @JsonProperty("total")
    private Integer total;

}