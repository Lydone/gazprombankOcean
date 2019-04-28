package com.lydone.gazprombankspringhack.data

import com.google.gson.annotations.SerializedName

data class NotificationData(
    val id: Int,
    val description: String,
    @SerializedName("ev_type") val type: String
)