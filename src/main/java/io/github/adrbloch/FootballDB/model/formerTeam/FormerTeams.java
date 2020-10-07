package io.github.adrbloch.FootballDB.model.formerTeam;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FormerTeams {

    @JsonProperty("formerteams")
    public List<FormerTeam> formerTeams = null;

}