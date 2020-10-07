package io.github.adrbloch.FootballDB.model.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Contracts {

    @JsonProperty("contracts")
    public List<Contract> contracts = null;

}