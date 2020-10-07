package io.github.adrbloch.FootballDB.model.formerTeam;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FormerTeam {

    @JsonProperty("id")
    public String id;
    @JsonProperty("idPlayer")
    public String idPlayer;
    @JsonProperty("idFormerTeam")
    public String idFormerTeam;
    @JsonProperty("strSport")
    public String strSport;
    @JsonProperty("strPlayer")
    public String strPlayer;
    @JsonProperty("strFormerTeam")
    public String strFormerTeam;
    @JsonProperty("strMoveType")
    public String strMoveType;
    @JsonProperty("strTeamBadge")
    public String strTeamBadge;
    @JsonProperty("strJoined")
    public String strJoined;
    @JsonProperty("strDeparted")
    public String strDeparted;

}