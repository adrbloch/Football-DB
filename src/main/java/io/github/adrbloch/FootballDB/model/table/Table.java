package io.github.adrbloch.FootballDB.model.table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Table {

    @JsonProperty("table")
    private List<TableTeam> table = null;

}