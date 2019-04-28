package com.lydone.gazprombankspringhack.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lydone.gazprombankspringhack.ApiService
import com.lydone.gazprombankspringhack.data.ChallengeData
import com.lydone.gazprombankspringhack.data.ProfileInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProfileViewModel : ViewModel() {
    private val mRetrofit = Retrofit.Builder()
        .baseUrl("http://10.20.2.219:8000/actum/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    private var isNeedToRefreshProfileInfo = true
    private var isNeedToRefreshDailyChallenges = true
    private var isNeedToRefreshSprintChallenges = true

    private val mApiService = mRetrofit.create(ApiService::class.java)

    private val mProfileInfoLiveData: MutableLiveData<ProfileInfo> = MutableLiveData()
    private val mDailyChallenges: MutableLiveData<List<ChallengeData>> = MutableLiveData()
    private val mSprintChallenges: MutableLiveData<List<ChallengeData>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getProfileInfoLiveData(): LiveData<ProfileInfo> {
        if (isNeedToRefreshProfileInfo) {
            isNeedToRefreshProfileInfo = false
            mApiService.getProfileInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { profileInfo -> mProfileInfoLiveData.postValue(profileInfo) }
        }
        return mProfileInfoLiveData
    }

    @SuppressLint("CheckResult")
    fun getDailyChallenges(): LiveData<List<ChallengeData>> {
        if (isNeedToRefreshDailyChallenges) {
            isNeedToRefreshDailyChallenges = false
            mApiService.getDailyChallenges()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { dailyChallenges -> mDailyChallenges.postValue(dailyChallenges) }
        }
        return mDailyChallenges
    }

    @SuppressLint("CheckResult")
    fun getSprintChallenges(): LiveData<List<ChallengeData>> {
        if (isNeedToRefreshSprintChallenges) {
            isNeedToRefreshSprintChallenges = false
            mApiService.getSprintChallenges()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { sprintChallenges -> mSprintChallenges.postValue(sprintChallenges) }
        }
        return mSprintChallenges
    }
}
