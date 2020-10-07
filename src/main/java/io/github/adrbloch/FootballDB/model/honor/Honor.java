package io.github.adrbloch.FootballDB.model.honor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Honor {

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
    @JsonProperty("strHonour")
    public String strHonor;
    @JsonProperty("strSeason")
    public String strSeason;

}
