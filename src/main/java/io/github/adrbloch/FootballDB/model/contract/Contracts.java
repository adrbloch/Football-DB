package io.github.adrbloch.FootballDB.model.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Contracts {

    @JsonProperty("contracts")
    private List<Contract> contracts = new ArrayList<>();

}