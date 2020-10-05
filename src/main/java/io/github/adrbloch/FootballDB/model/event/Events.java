package io.github.adrbloch.FootballDB.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Events {

    @JsonProperty("event")
    public List<Event> event = null;

}