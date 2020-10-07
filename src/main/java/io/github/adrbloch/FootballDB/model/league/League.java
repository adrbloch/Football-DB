package io.github.adrbloch.FootballDB.model.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class League {

    @JsonProperty("idLeague")
    private String idLeague;
    @JsonProperty("idSoccerXML")
    private String idSoccerXML;
    @JsonProperty("idAPIfootball")
    private String idAPIfootball;
    @JsonProperty("strSport")
    private String strSport;
    @JsonProperty("strLeague")
    private String strLeague;
    @JsonProperty("strLeagueAlternate")
    private String strLeagueAlternate;
    @JsonProperty("strDivision")
    private String strDivision;
    @JsonProperty("idCup")
    private String idCup;
    @JsonProperty("strCurrentSeason")
    private String strCurrentSeason;
    @JsonProperty("intFormedYear")
    private String intFormedYear;
    @JsonProperty("dateFirstEvent")
    private String dateFirstEvent;
    @JsonProperty("strGender")
    private String strGender;
    @JsonProperty("strCountry")
    private String strCountry;
    @JsonProperty("strWebsite")
    private String strWebsite;
    @JsonProperty("strFacebook")
    private String strFacebook;
    @JsonProperty("strTwitter")
    private String strTwitter;
    @JsonProperty("strYoutube")
    private String strYoutube;
    @JsonProperty("strRSS")
    private String strRSS;
    @JsonProperty("strDescriptionEN")
    private String strDescriptionEN;
    @JsonProperty("strDescriptionDE")
    private String strDescriptionDE;
    @JsonProperty("strDescriptionFR")
    private String strDescriptionFR;
    @JsonProperty("strDescriptionIT")
    private String strDescriptionIT;
    @JsonProperty("strDescriptionCN")
    private String strDescriptionCN;
    @JsonProperty("strDescriptionJP")
    private String strDescriptionJP;
    @JsonProperty("strDescriptionRU")
    private String strDescriptionRU;
    @JsonProperty("strDescriptionES")
    private String strDescriptionES;
    @JsonProperty("strDescriptionPT")
    private String strDescriptionPT;
    @JsonProperty("strDescriptionSE")
    private String strDescriptionSE;
    @JsonProperty("strDescriptionNL")
    private String strDescriptionNL;
    @JsonProperty("strDescriptionHU")
    private String strDescriptionHU;
    @JsonProperty("strDescriptionNO")
    private String strDescriptionNO;
    @JsonProperty("strDescriptionPL")
    private String strDescriptionPL;
    @JsonProperty("strDescriptionIL")
    private String strDescriptionIL;
    @JsonProperty("strFanart1")
    private String strFanart1;
    @JsonProperty("strFanart2")
    private String strFanart2;
    @JsonProperty("strFanart3")
    private String strFanart3;
    @JsonProperty("strFanart4")
    private String strFanart4;
    @JsonProperty("strBanner")
    private String strBanner;
    @JsonProperty("strBadge")
    private String strBadge;
    @JsonProperty("strLogo")
    private String strLogo;
    @JsonProperty("strPoster")
    private String strPoster;
    @JsonProperty("strTrophy")
    private String strTrophy;
    @JsonProperty("strNaming")
    private String strNaming;
    @JsonProperty("strComplete")
    private String strComplete;
    @JsonProperty("strLocked")
    private String strLocked;

}
