package io.github.adrbloch.FootballDB.model.formerTeam;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FormerTeam {

    @JsonProperty("id")
    private String id;
    @JsonProperty("idPlayer")
    private String idPlayer;
    @JsonProperty("idFormerTeam")
    private String idFormerTeam;
    @JsonProperty("strSport")
    private String strSport;
    @JsonProperty("strPlayer")
    private String strPlayer;
    @JsonProperty("strFormerTeam")
    private String strFormerTeam;
    @JsonProperty("strMoveType")
    private String strMoveType;
    @JsonProperty("strTeamBadge")
    private String strTeamBadge;
    @JsonProperty("strJoined")
    private String strJoined;
    @JsonProperty("strDeparted")
    private String strDeparted;

}