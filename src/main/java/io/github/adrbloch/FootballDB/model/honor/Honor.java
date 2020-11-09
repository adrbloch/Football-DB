package io.github.adrbloch.FootballDB.model.honor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Honor {

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
    @JsonProperty("strHonour")
    private String strHonor;
    @JsonProperty("strSeason")
    private String strSeason;

}
