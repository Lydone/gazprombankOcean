package com.lydone.gazprombankspringhack

import com.lydone.gazprombankspringhack.data.ChallengeData
import com.lydone.gazprombankspringhack.data.NotificationData
import com.lydone.gazprombankspringhack.data.ProfileInfo
import com.lydone.gazprombankspringhack.data.RatingPositionData
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiService {
    @GET("employee/6/")
    fun getProfileInfo(): Single<ProfileInfo>

    @GET("challenges/daily")
    fun getDailyChallenges(): Single<List<ChallengeData>>

    @GET("challenges/sprint")
    fun getSprintChallenges(): Single<List<ChallengeData>>

    @GET("events/")
    fun getNotifications(): Single<List<NotificationData>>

    @GET("rating/team")
    fun getTeamRating(): Single<List<RatingPositionData>>

    @GET("rating/employee")
    fun getPrivateRating(): Single<List<RatingPositionData>>
}