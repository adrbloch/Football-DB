package io.github.adrbloch.FootballDB.model.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    @JsonProperty("idPlayer")
    public String idPlayer;
    @JsonProperty("idTeam")
    public String idTeam;
    @JsonProperty("idTeam2")
    public String idTeam2;
    @JsonProperty("idTeamNational")
    public String idTeamNational;
    @JsonProperty("idSoccerXML")
    public String idSoccerXML;
    @JsonProperty("idAPIfootball")
    public String idAPIfootball;
    @JsonProperty("idPlayerManager")
    public String idPlayerManager;
    @JsonProperty("strNationality")
    public String strNationality;
    @JsonProperty("strPlayer")
    public String strPlayer;
    @JsonProperty("strTeam")
    public String strTeam;
    @JsonProperty("strTeam2")
    public String strTeam2;
    @JsonProperty("strSport")
    public String strSport;
    @JsonProperty("intSoccerXMLTeamID")
    public String intSoccerXMLTeamID;
    @JsonProperty("dateBorn")
    public String dateBorn;
    @JsonProperty("strNumber")
    public String strNumber;
    @JsonProperty("dateSigned")
    public String dateSigned;
    @JsonProperty("strSigning")
    public String strSigning;
    @JsonProperty("strWage")
    public String strWage;
    @JsonProperty("strOutfitter")
    public String strOutfitter;
    @JsonProperty("strKit")
    public String strKit;
    @JsonProperty("strAgent")
    public String strAgent;
    @JsonProperty("strBirthLocation")
    public String strBirthLocation;
    @JsonProperty("strDescriptionEN")
    public String strDescriptionEN;
    @JsonProperty("strDescriptionDE")
    public String strDescriptionDE;
    @JsonProperty("strDescriptionFR")
    public String strDescriptionFR;
    @JsonProperty("strDescriptionCN")
    public String strDescriptionCN;
    @JsonProperty("strDescriptionIT")
    public String strDescriptionIT;
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
    @JsonProperty("strDescriptionIL")
    public String strDescriptionIL;
    @JsonProperty("strDescriptionPL")
    public String strDescriptionPL;
    @JsonProperty("strGender")
    public String strGender;
    @JsonProperty("strSide")
    public String strSide;
    @JsonProperty("strPosition")
    public String strPosition;
    @JsonProperty("strCollege")
    public String strCollege;
    @JsonProperty("strFacebook")
    public String strFacebook;
    @JsonProperty("strWebsite")
    public String strWebsite;
    @JsonProperty("strTwitter")
    public String strTwitter;
    @JsonProperty("strInstagram")
    public String strInstagram;
    @JsonProperty("strYoutube")
    public String strYoutube;
    @JsonProperty("strHeight")
    public String strHeight;
    @JsonProperty("strWeight")
    public String strWeight;
    @JsonProperty("intLoved")
    public String intLoved;
    @JsonProperty("strThumb")
    public String strThumb;
    @JsonProperty("strCutout")
    public String strCutout;
    @JsonProperty("strRender")
    public String strRender;
    @JsonProperty("strBanner")
    public String strBanner;
    @JsonProperty("strFanart1")
    public String strFanart1;
    @JsonProperty("strFanart2")
    public String strFanart2;
    @JsonProperty("strFanart3")
    public String strFanart3;
    @JsonProperty("strFanart4")
    public String strFanart4;
    @JsonProperty("strCreativeCommons")
    public String strCreativeCommons;
    @JsonProperty("strLocked")
    public String strLocked;

}
