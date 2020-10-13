package io.github.adrbloch.FootballDB.model.honor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Honors {

    @JsonProperty("honors")
    private List<Honor> honors = null;

}