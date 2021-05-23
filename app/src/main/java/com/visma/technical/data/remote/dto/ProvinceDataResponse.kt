package com.visma.technical.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProvinceDataResponse(
    @Expose
    @SerializedName("today")
    val description : String,

    @Expose
    @SerializedName("ciudades")
    val ciudades: List<CityResponse>
)


data class CityResponse(

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("temperatures")
    val temperatures: TemperatureResponse
)

data class TemperatureResponse(

    @Expose
    @SerializedName("max")
    val max: String,

    @Expose
    @SerializedName("min")
    val min: String
)