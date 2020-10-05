package io.github.adrbloch.FootballDB.model.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Teams {

    @JsonProperty("teams")
    private List<Team> teams = null;

}