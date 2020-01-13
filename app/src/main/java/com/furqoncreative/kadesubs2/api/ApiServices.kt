package com.furqoncreative.kadesubs2.api

import com.furqoncreative.kadesubs2.model.LeagueResponse
import com.furqoncreative.kadesubs2.model.MatchResponse
import com.furqoncreative.kadesubs2.model.SearchResponse
import com.furqoncreative.kadesubs2.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") id: String?): Call<LeagueResponse>

    @GET("lookupevent.php")
    fun getDetailMatch(@Query("id") id: String?): Call<MatchResponse>

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") id: String?): Call<TeamResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String?): Call<MatchResponse>

    @GET("eventspastleague.php")
    fun getPrevMatch(@Query("id") id: String?): Call<MatchResponse>

    @GET("searchevents.php")
    fun getResultSearch(@Query("e") query: String?): Call<SearchResponse>

}