package com.furqoncreative.kadesubs2.model


import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("dateFirstEvent")
    val dateFirstEvent: String,
    @SerializedName("idAPIfootball")
    val idAPIfootball: Any,
    @SerializedName("idCup")
    val idCup: String,
    @SerializedName("idLeague")
    val idLeague: String,
    @SerializedName("idSoccerXML")
    val idSoccerXML: String,
    @SerializedName("intFormedYear")
    val intFormedYear: String,
    @SerializedName("strBadge")
    val strBadge: String,
    @SerializedName("strBanner")
    val strBanner: String,
    @SerializedName("strComplete")
    val strComplete: String,
    @SerializedName("strCountry")
    val strCountry: String,
    @SerializedName("strDescriptionCN")
    val strDescriptionCN: String,
    @SerializedName("strDescriptionDE")
    val strDescriptionDE: String,
    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String,
    @SerializedName("strDescriptionES")
    val strDescriptionES: String,
    @SerializedName("strDescriptionFR")
    val strDescriptionFR: String,
    @SerializedName("strDescriptionHU")
    val strDescriptionHU: String,
    @SerializedName("strDescriptionIL")
    val strDescriptionIL: String,
    @SerializedName("strDescriptionIT")
    val strDescriptionIT: String,
    @SerializedName("strDescriptionJP")
    val strDescriptionJP: String,
    @SerializedName("strDescriptionNL")
    val strDescriptionNL: String,
    @SerializedName("strDescriptionNO")
    val strDescriptionNO: String,
    @SerializedName("strDescriptionPL")
    val strDescriptionPL: String,
    @SerializedName("strDescriptionPT")
    val strDescriptionPT: String,
    @SerializedName("strDescriptionRU")
    val strDescriptionRU: String,
    @SerializedName("strDescriptionSE")
    val strDescriptionSE: String,
    @SerializedName("strDivision")
    val strDivision: String,
    @SerializedName("strFacebook")
    val strFacebook: String,
    @SerializedName("strFanart1")
    val strFanart1: String,
    @SerializedName("strFanart2")
    val strFanart2: String,
    @SerializedName("strFanart3")
    val strFanart3: String,
    @SerializedName("strFanart4")
    val strFanart4: String,
    @SerializedName("strGender")
    val strGender: String,
    @SerializedName("strLeague")
    val strLeague: String,
    @SerializedName("strLeagueAlternate")
    val strLeagueAlternate: String,
    @SerializedName("strLocked")
    val strLocked: String,
    @SerializedName("strLogo")
    val strLogo: String,
    @SerializedName("strNaming")
    val strNaming: String,
    @SerializedName("strPoster")
    val strPoster: String,
    @SerializedName("strRSS")
    val strRSS: String,
    @SerializedName("strSport")
    val strSport: String,
    @SerializedName("strTrophy")
    val strTrophy: String,
    @SerializedName("strTwitter")
    val strTwitter: String,
    @SerializedName("strWebsite")
    val strWebsite: String,
    @SerializedName("strYoutube")
    val strYoutube: String
)