package com.lydone.gazprombankspringhack.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.lydone.gazprombankspringhack.ApiService
import com.lydone.gazprombankspringhack.data.NotificationData
import com.lydone.gazprombankspringhack.data.RatingPositionData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TeamRatingViewModel : ViewModel() {
    private val mRetrofit = Retrofit.Builder()
            .baseUrl("http://10.20.2.219:8000/actum/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private var isNeedToRefreshTeamRating = true

    private val mApiService = mRetrofit.create(ApiService::class.java)
    private val mTeamRatingLiveData: MutableLiveData<List<RatingPositionData>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getTeamRatingLiveData(): LiveData<List<RatingPositionData>> {
        if (isNeedToRefreshTeamRating) {
            isNeedToRefreshTeamRating = false
            mApiService.getTeamRating()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { teamRating -> mTeamRatingLiveData.postValue(teamRating) }
        }
        return mTeamRatingLiveData
    }
}
