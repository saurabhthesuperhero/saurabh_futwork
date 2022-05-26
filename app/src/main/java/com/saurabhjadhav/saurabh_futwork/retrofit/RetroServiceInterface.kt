package com.saurabhjadhav.saurabh_futwork.retrofit

import com.saurabhjadhav.saurabh_futwork.data.TeleProjectModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("projects")
    fun getTeleProjectsList(): Call<ArrayList<TeleProjectModel>>
}