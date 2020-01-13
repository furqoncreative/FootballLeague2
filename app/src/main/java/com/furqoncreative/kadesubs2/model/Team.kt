package com.furqoncreative.kadesubs2.model


import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idAPIfootball")
    val idAPIfootball: String,
    @SerializedName("idLeague")
    val idLeague: String,
    @SerializedName("idSoccerXML")
    val idSoccerXML: String,
    @SerializedName("idTeam")
    val idTeam: String,
    @SerializedName("intFormedYear")
    val intFormedYear: String,
    @SerializedName("intLoved")
    val intLoved: String,
    @SerializedName("intStadiumCapacity")
    val intStadiumCapacity: String,
    @SerializedName("strAlternate")
    val strAlternate: String,
    @SerializedName("strCountry")
    val strCountry: String,
    @SerializedName("strDescriptionCN")
    val strDescriptionCN: Any,
    @SerializedName("strDescriptionDE")
    val strDescriptionDE: String,
    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String,
    @SerializedName("strDescriptionES")
    val strDescriptionES: Any,
    @SerializedName("strDescriptionFR")
    val strDescriptionFR: Any,
    @SerializedName("strDescriptionHU")
    val strDescriptionHU: Any,
    @SerializedName("strDescriptionIL")
    val strDescriptionIL: Any,
    @SerializedName("strDescriptionIT")
    val strDescriptionIT: String,
    @SerializedName("strDescriptionJP")
    val strDescriptionJP: Any,
    @SerializedName("strDescriptionNL")
    val strDescriptionNL: Any,
    @SerializedName("strDescriptionNO")
    val strDescriptionNO: Any,
    @SerializedName("strDescriptionPL")
    val strDescriptionPL: Any,
    @SerializedName("strDescriptionPT")
    val strDescriptionPT: Any,
    @SerializedName("strDescriptionRU")
    val strDescriptionRU: Any,
    @SerializedName("strDescriptionSE")
    val strDescriptionSE: Any,
    @SerializedName("strDivision")
    val strDivision: Any,
    @SerializedName("strFacebook")
    val strFacebook: String,
    @SerializedName("strGender")
    val strGender: String,
    @SerializedName("strInstagram")
    val strInstagram: String,
    @SerializedName("strKeywords")
    val strKeywords: String,
    @SerializedName("strLeague")
    val strLeague: String,
    @SerializedName("strLocked")
    val strLocked: String,
    @SerializedName("strManager")
    val strManager: String,
    @SerializedName("strRSS")
    val strRSS: String,
    @SerializedName("strSport")
    val strSport: String,
    @SerializedName("strStadium")
    val strStadium: String,
    @SerializedName("strStadiumDescription")
    val strStadiumDescription: String,
    @SerializedName("strStadiumLocation")
    val strStadiumLocation: String,
    @SerializedName("strStadiumThumb")
    val strStadiumThumb: String,
    @SerializedName("strTeam")
    val strTeam: String,
    @SerializedName("strTeamBadge")
    val strTeamBadge: String,
    @SerializedName("strTeamBanner")
    val strTeamBanner: String,
    @SerializedName("strTeamFanart1")
    val strTeamFanart1: String,
    @SerializedName("strTeamFanart2")
    val strTeamFanart2: String,
    @SerializedName("strTeamFanart3")
    val strTeamFanart3: String,
    @SerializedName("strTeamFanart4")
    val strTeamFanart4: String,
    @SerializedName("strTeamJersey")
    val strTeamJersey: String,
    @SerializedName("strTeamLogo")
    val strTeamLogo: String,
    @SerializedName("strTeamShort")
    val strTeamShort: String,
    @SerializedName("strTwitter")
    val strTwitter: String,
    @SerializedName("strWebsite")
    val strWebsite: String,
    @SerializedName("strYoutube")
    val strYoutube: String
)