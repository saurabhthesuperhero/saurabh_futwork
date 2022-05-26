package com.saurabhjadhav.saurabh_futwork.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saurabhjadhav.saurabh_futwork.data.TeleProjectModel
import com.saurabhjadhav.saurabh_futwork.retrofit.RetroInstance
import com.saurabhjadhav.saurabh_futwork.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivityViewModel : ViewModel() {
    lateinit var liveDataList: MutableLiveData<ArrayList<TeleProjectModel>>

    init {
        liveDataList = MutableLiveData()

    }

    fun getLiveDataObserver(): MutableLiveData<ArrayList<TeleProjectModel>> {
        return liveDataList
    }

    fun makeApiCall() {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getTeleProjectsList()
        call.enqueue(object :
            retrofit2.Callback<ArrayList<TeleProjectModel>> {
            override fun onResponse(
                call: Call<ArrayList<TeleProjectModel>>,
                response: Response<ArrayList<TeleProjectModel>>
            ) {
                Log.e("checkData", "onResponse: "+response.body() )
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<TeleProjectModel>>, t: Throwable) {
                Log.e("checkData", "onFailure: "+t)
                liveDataList.postValue(null)
            }


        })
    }
}