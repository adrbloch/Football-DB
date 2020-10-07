package io.github.adrbloch.FootballDB.model.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Events {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("event")
    private List<Event> event = null;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("events")
    private List<Event> events = null;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("results")
    private List<Event> lastEvents = null;

}