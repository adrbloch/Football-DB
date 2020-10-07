package io.github.adrbloch.FootballDB.model.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contract {

    @JsonProperty("id")
    public String id;
    @JsonProperty("idPlayer")
    public String idPlayer;
    @JsonProperty("idTeam")
    public String idTeam;
    @JsonProperty("strSport")
    public String strSport;
    @JsonProperty("strPlayer")
    public String strPlayer;
    @JsonProperty("strTeam")
    public String strTeam;
    @JsonProperty("strTeamBadge")
    public String strTeamBadge;
    @JsonProperty("strYearStart")
    public String strYearStart;
    @JsonProperty("strYearEnd")
    public String strYearEnd;
    @JsonProperty("strWage")
    public String strWage;

}
