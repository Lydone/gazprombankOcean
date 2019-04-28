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

class PrivateRatingViewModel : ViewModel() {
    private val mRetrofit = Retrofit.Builder()
            .baseUrl("http://10.20.2.219:8000/actum/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private var isNeedToRefreshPrivateRating = true

    private val mApiService = mRetrofit.create(ApiService::class.java)
    private val mPrivateRatingLiveData: MutableLiveData<List<RatingPositionData>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getPrivateRatingLiveData(): LiveData<List<RatingPositionData>> {
        if (isNeedToRefreshPrivateRating) {
            isNeedToRefreshPrivateRating = false
            mApiService.getPrivateRating()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { privateRating -> mPrivateRatingLiveData.postValue(privateRating) }
        }
        return mPrivateRatingLiveData
    }
}
