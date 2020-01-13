package com.furqoncreative.kadesubs2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueItem(
    val id: String?, val name: String?, val logo: Int?
) : Parcelable