package io.github.adrbloch.FootballDB.model.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class League {

    @JsonProperty("idLeague")
    public String idLeague;
    @JsonProperty("idSoccerXML")
    public String idSoccerXML;
    @JsonProperty("idAPIfootball")
    public String idAPIfootball;
    @JsonProperty("strSport")
    public String strSport;
    @JsonProperty("strLeague")
    public String strLeague;
    @JsonProperty("strLeagueAlternate")
    public String strLeagueAlternate;
    @JsonProperty("strDivision")
    public String strDivision;
    @JsonProperty("idCup")
    public String idCup;
    @JsonProperty("strCurrentSeason")
    public String strCurrentSeason;
    @JsonProperty("intFormedYear")
    public String intFormedYear;
    @JsonProperty("dateFirstEvent")
    public String dateFirstEvent;
    @JsonProperty("strGender")
    public String strGender;
    @JsonProperty("strCountry")
    public String strCountry;
    @JsonProperty("strWebsite")
    public String strWebsite;
    @JsonProperty("strFacebook")
    public String strFacebook;
    @JsonProperty("strTwitter")
    public String strTwitter;
    @JsonProperty("strYoutube")
    public String strYoutube;
    @JsonProperty("strRSS")
    public String strRSS;
    @JsonProperty("strDescriptionEN")
    public String strDescriptionEN;
    @JsonProperty("strDescriptionDE")
    public String strDescriptionDE;
    @JsonProperty("strDescriptionFR")
    public String strDescriptionFR;
    @JsonProperty("strDescriptionIT")
    public String strDescriptionIT;
    @JsonProperty("strDescriptionCN")
    public String strDescriptionCN;
    @JsonProperty("strDescriptionJP")
    public String strDescriptionJP;
    @JsonProperty("strDescriptionRU")
    public String strDescriptionRU;
    @JsonProperty("strDescriptionES")
    public String strDescriptionES;
    @JsonProperty("strDescriptionPT")
    public String strDescriptionPT;
    @JsonProperty("strDescriptionSE")
    public String strDescriptionSE;
    @JsonProperty("strDescriptionNL")
    public String strDescriptionNL;
    @JsonProperty("strDescriptionHU")
    public String strDescriptionHU;
    @JsonProperty("strDescriptionNO")
    public String strDescriptionNO;
    @JsonProperty("strDescriptionPL")
    public String strDescriptionPL;
    @JsonProperty("strDescriptionIL")
    public String strDescriptionIL;
    @JsonProperty("strFanart1")
    public String strFanart1;
    @JsonProperty("strFanart2")
    public String strFanart2;
    @JsonProperty("strFanart3")
    public String strFanart3;
    @JsonProperty("strFanart4")
    public String strFanart4;
    @JsonProperty("strBanner")
    public String strBanner;
    @JsonProperty("strBadge")
    public String strBadge;
    @JsonProperty("strLogo")
    public String strLogo;
    @JsonProperty("strPoster")
    public String strPoster;
    @JsonProperty("strTrophy")
    public String strTrophy;
    @JsonProperty("strNaming")
    public String strNaming;
    @JsonProperty("strComplete")
    public String strComplete;
    @JsonProperty("strLocked")
    public String strLocked;

}
