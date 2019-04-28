package com.lydone.gazprombankspringhack.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.lydone.gazprombankspringhack.ApiService
import com.lydone.gazprombankspringhack.data.NotificationData
import com.lydone.gazprombankspringhack.data.ProfileInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NotificationsViewModel : ViewModel() {
    private val mRetrofit = Retrofit.Builder()
        .baseUrl("http://10.20.2.219:8000/actum/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private var isNeedToRefreshNotifications = true

    private val mApiService = mRetrofit.create(ApiService::class.java)
    private val mNotificationsLiveData: MutableLiveData<List<NotificationData>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getNotificationsLiveData(): LiveData<List<NotificationData>> {
        if (isNeedToRefreshNotifications) {
            isNeedToRefreshNotifications = false
            mApiService.getNotifications()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { notifications -> mNotificationsLiveData.postValue(notifications) }
        }
        return mNotificationsLiveData
    }


}
