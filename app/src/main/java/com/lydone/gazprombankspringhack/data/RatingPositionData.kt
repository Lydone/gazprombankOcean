package com.lydone.gazprombankspringhack.data

import com.google.gson.annotations.SerializedName

data class RatingPositionData(
    val name: String, val rating: Int,
    @SerializedName("points_per_day") val points: Int
)