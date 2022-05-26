package com.saurabhjadhav.saurabh_futwork.data

import com.google.gson.annotations.SerializedName

data class TeleProjectModel(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("earning") val earning: Double,
    @SerializedName("logo") val logo: String
)