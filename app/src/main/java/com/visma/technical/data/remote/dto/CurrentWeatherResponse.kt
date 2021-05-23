package com.visma.technical.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(

    @Expose
    @SerializedName("data")
    val data: DataResponse
)

data class DataResponse(

    @Expose
    @SerializedName("current_condition")
    val currentCondition: List<CurrentConditionResponse>

)

data class CurrentConditionResponse(
    @Expose
    @SerializedName("temp_C")
    val temp: String
)