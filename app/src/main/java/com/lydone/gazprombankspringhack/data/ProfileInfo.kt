package com.lydone.gazprombankspringhack.data

import com.google.gson.annotations.SerializedName

data class ProfileInfo(
    val id: Int,
    val mult: Int,
    val name: String,
    val position: String,
    val team: String,
    @SerializedName("empl_rating") val employeeRating: Int,
    @SerializedName("team_rating") val teamRating: Int,
    @SerializedName("tasks_per_day") val tasksPerDay: Int,
    @SerializedName("tasks_average") val tasksAverage: Int,
    @SerializedName("points_per_day") val pointsPerDay: Int,
    @SerializedName("points_average") val pointsAverage: Int,
    @SerializedName("team_tasks_per_day") val teamTasksPerDay: Int,
    @SerializedName("team_tasks_average") val teamTasksAverage: Int,
    @SerializedName("team_points_per_day") val teamPointsPerDay: Int,
    @SerializedName("team_points_average") val teamPointsAverage: Int
)