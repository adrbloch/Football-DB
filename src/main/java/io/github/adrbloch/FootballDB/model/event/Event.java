package io.github.adrbloch.FootballDB.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {

    @JsonProperty("idEvent")
    public String idEvent;
    @JsonProperty("idSoccerXML")
    public String idSoccerXML;
    @JsonProperty("idAPIfootball")
    public String idAPIfootball;
    @JsonProperty("strEvent")
    public String strEvent;
    @JsonProperty("strEventAlternate")
    public String strEventAlternate;
    @JsonProperty("strFilename")
    public String strFilename;
    @JsonProperty("strSport")
    public String strSport;
    @JsonProperty("idLeague")
    public String idLeague;
    @JsonProperty("strLeague")
    public String strLeague;
    @JsonProperty("strSeason")
    public String strSeason;
    @JsonProperty("strDescriptionEN")
    public String strDescriptionEN;
    @JsonProperty("strHomeTeam")
    public String strHomeTeam;
    @JsonProperty("strAwayTeam")
    public String strAwayTeam;
    @JsonProperty("intHomeScore")
    public String intHomeScore;
    @JsonProperty("intRound")
    public String intRound;
    @JsonProperty("intAwayScore")
    public String intAwayScore;
    @JsonProperty("intSpectators")
    public String intSpectators;
    @JsonProperty("strOfficial")
    public String strOfficial;
    @JsonProperty("strHomeGoalDetails")
    public String strHomeGoalDetails;
    @JsonProperty("strHomeRedCards")
    public String strHomeRedCards;
    @JsonProperty("strHomeYellowCards")
    public String strHomeYellowCards;
    @JsonProperty("strHomeLineupGoalkeeper")
    public String strHomeLineupGoalkeeper;
    @JsonProperty("strHomeLineupDefense")
    public String strHomeLineupDefense;
    @JsonProperty("strHomeLineupMidfield")
    public String strHomeLineupMidfield;
    @JsonProperty("strHomeLineupForward")
    public String strHomeLineupForward;
    @JsonProperty("strHomeLineupSubstitutes")
    public String strHomeLineupSubstitutes;
    @JsonProperty("strHomeFormation")
    public String strHomeFormation;
    @JsonProperty("strAwayRedCards")
    public String strAwayRedCards;
    @JsonProperty("strAwayYellowCards")
    public String strAwayYellowCards;
    @JsonProperty("strAwayGoalDetails")
    public String strAwayGoalDetails;
    @JsonProperty("strAwayLineupGoalkeeper")
    public String strAwayLineupGoalkeeper;
    @JsonProperty("strAwayLineupDefense")
    public String strAwayLineupDefense;
    @JsonProperty("strAwayLineupMidfield")
    public String strAwayLineupMidfield;
    @JsonProperty("strAwayLineupForward")
    public String strAwayLineupForward;
    @JsonProperty("strAwayLineupSubstitutes")
    public String strAwayLineupSubstitutes;
    @JsonProperty("strAwayFormation")
    public String strAwayFormation;
    @JsonProperty("intHomeShots")
    public String intHomeShots;
    @JsonProperty("intAwayShots")
    public String intAwayShots;
    @JsonProperty("strTimestamp")
    public String strTimestamp;
    @JsonProperty("dateEvent")
    public String dateEvent;
    @JsonProperty("dateEventLocal")
    public String dateEventLocal;
    @JsonProperty("strDate")
    public String strDate;
    @JsonProperty("strTime")
    public String strTime;
    @JsonProperty("strTimeLocal")
    public String strTimeLocal;
    @JsonProperty("strTVStation")
    public String strTVStation;
    @JsonProperty("idHomeTeam")
    public String idHomeTeam;
    @JsonProperty("idAwayTeam")
    public String idAwayTeam;
    @JsonProperty("strResult")
    public String strResult;
    @JsonProperty("strVenue")
    public String strVenue;
    @JsonProperty("strCountry")
    public String strCountry;
    @JsonProperty("strCity")
    public String strCity;
    @JsonProperty("strPoster")
    public String strPoster;
    @JsonProperty("strFanart")
    public String strFanart;
    @JsonProperty("strThumb")
    public String strThumb;
    @JsonProperty("strBanner")
    public String strBanner;
    @JsonProperty("strMap")
    public String strMap;
    @JsonProperty("strTweet1")
    public String strTweet1;
    @JsonProperty("strTweet2")
    public String strTweet2;
    @JsonProperty("strTweet3")
    public String strTweet3;
    @JsonProperty("strVideo")
    public String strVideo;
    @JsonProperty("strStatus")
    public String strStatus;
    @JsonProperty("strPostponed")
    public String strPostponed;
    @JsonProperty("strLocked")
    public String strLocked;

}
