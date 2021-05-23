package com.visma.technical.data.remote

import com.visma.technical.data.remote.dto.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocalWeatherService {


    @GET("premium/v1/weather.ashx/")
    suspend fun currentWeather(
        @Query("key") key: String,
        @Query("q") latlog: String,
        @Query("format") format: String = "json"
    ): Response<CurrentWeatherResponse>
}