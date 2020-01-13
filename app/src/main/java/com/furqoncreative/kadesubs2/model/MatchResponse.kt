package com.furqoncreative.kadesubs2.model


import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("events")
    val events: List<Match>
)