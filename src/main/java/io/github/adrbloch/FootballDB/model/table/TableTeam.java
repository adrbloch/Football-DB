package io.github.adrbloch.FootballDB.model.table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableTeam {

    @JsonProperty("idStanding")
    public String idStanding;
    @JsonProperty("intRank")
    public String intRank;
    @JsonProperty("idTeam")
    public String idTeam;
    @JsonProperty("strTeam")
    public String strTeam;
    @JsonProperty("strTeamBadge")
    public String strTeamBadge;
    @JsonProperty("idLeague")
    public String idLeague;
    @JsonProperty("strLeague")
    public String strLeague;
    @JsonProperty("strSeason")
    public String strSeason;
    @JsonProperty("strForm")
    public String strForm;
    @JsonProperty("strDescription")
    public String strDescription;
    @JsonProperty("intPlayed")
    public String intPlayed;
    @JsonProperty("intWin")
    public String intWin;
    @JsonProperty("intLoss")
    public String intLoss;
    @JsonProperty("intDraw")
    public String intDraw;
    @JsonProperty("intGoalsFor")
    public String intGoalsFor;
    @JsonProperty("intGoalsAgainst")
    public String intGoalsAgainst;
    @JsonProperty("intGoalDifference")
    public String intGoalDifference;
    @JsonProperty("intPoints")
    public String intPoints;
    @JsonProperty("dateUpdated")
    public String dateUpdated;

}