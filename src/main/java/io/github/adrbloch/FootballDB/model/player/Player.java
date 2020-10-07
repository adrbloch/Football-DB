package io.github.adrbloch.FootballDB.model.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    @JsonProperty("idPlayer")
    private String idPlayer;
    @JsonProperty("idTeam")
    private String idTeam;
    @JsonProperty("idTeam2")
    private String idTeam2;
    @JsonProperty("idTeamNational")
    private String idTeamNational;
    @JsonProperty("idSoccerXML")
    private String idSoccerXML;
    @JsonProperty("idAPIfootball")
    private String idAPIfootball;
    @JsonProperty("idPlayerManager")
    private String idPlayerManager;
    @JsonProperty("strNationality")
    private String strNationality;
    @JsonProperty("strPlayer")
    private String strPlayer;
    @JsonProperty("strTeam")
    private String strTeam;
    @JsonProperty("strTeam2")
    private String strTeam2;
    @JsonProperty("strSport")
    private String strSport;
    @JsonProperty("intSoccerXMLTeamID")
    private String intSoccerXMLTeamID;
    @JsonProperty("dateBorn")
    private String dateBorn;
    @JsonProperty("strNumber")
    private String strNumber;
    @JsonProperty("dateSigned")
    private String dateSigned;
    @JsonProperty("strSigning")
    private String strSigning;
    @JsonProperty("strWage")
    private String strWage;
    @JsonProperty("strOutfitter")
    private String strOutfitter;
    @JsonProperty("strKit")
    private String strKit;
    @JsonProperty("strAgent")
    private String strAgent;
    @JsonProperty("strBirthLocation")
    private String strBirthLocation;
    @JsonProperty("strDescriptionEN")
    private String strDescriptionEN;
    @JsonProperty("strDescriptionDE")
    private String strDescriptionDE;
    @JsonProperty("strDescriptionFR")
    private String strDescriptionFR;
    @JsonProperty("strDescriptionCN")
    private String strDescriptionCN;
    @JsonProperty("strDescriptionIT")
    private String strDescriptionIT;
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
    @JsonProperty("strDescriptionIL")
    private String strDescriptionIL;
    @JsonProperty("strDescriptionPL")
    private String strDescriptionPL;
    @JsonProperty("strGender")
    private String strGender;
    @JsonProperty("strSide")
    private String strSide;
    @JsonProperty("strPosition")
    private String strPosition;
    @JsonProperty("strCollege")
    private String strCollege;
    @JsonProperty("strFacebook")
    private String strFacebook;
    @JsonProperty("strWebsite")
    private String strWebsite;
    @JsonProperty("strTwitter")
    private String strTwitter;
    @JsonProperty("strInstagram")
    private String strInstagram;
    @JsonProperty("strYoutube")
    private String strYoutube;
    @JsonProperty("strHeight")
    private String strHeight;
    @JsonProperty("strWeight")
    private String strWeight;
    @JsonProperty("intLoved")
    private String intLoved;
    @JsonProperty("strThumb")
    private String strThumb;
    @JsonProperty("strCutout")
    private String strCutout;
    @JsonProperty("strRender")
    private String strRender;
    @JsonProperty("strBanner")
    private String strBanner;
    @JsonProperty("strFanart1")
    private String strFanart1;
    @JsonProperty("strFanart2")
    private String strFanart2;
    @JsonProperty("strFanart3")
    private String strFanart3;
    @JsonProperty("strFanart4")
    private String strFanart4;
    @JsonProperty("strCreativeCommons")
    private String strCreativeCommons;
    @JsonProperty("strLocked")
    private String strLocked;

}
