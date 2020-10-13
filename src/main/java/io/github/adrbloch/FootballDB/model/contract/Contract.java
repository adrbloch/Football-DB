package io.github.adrbloch.FootballDB.model.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contract {

    @JsonProperty("id")
    private String id;
    @JsonProperty("idPlayer")
    private String idPlayer;
    @JsonProperty("idTeam")
    private String idTeam;
    @JsonProperty("strSport")
    private String strSport;
    @JsonProperty("strPlayer")
    private String strPlayer;
    @JsonProperty("strTeam")
    private String strTeam;
    @JsonProperty("strTeamBadge")
    private String strTeamBadge;
    @JsonProperty("strYearStart")
    private String strYearStart;
    @JsonProperty("strYearEnd")
    private String strYearEnd;
    @JsonProperty("strWage")
    private String strWage;

}
