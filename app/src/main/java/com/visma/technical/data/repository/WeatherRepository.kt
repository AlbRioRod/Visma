package com.visma.technical.data.repository

import com.visma.technical.data.source.RemoteDataSource
import com.visma.technical.domain.model.CurrentWeatherDomain
import com.visma.technical.domain.model.ProvinceDataDomain
import com.visma.technical.domain.model.ProvincesDomain
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherDataSource: RemoteDataSource
) {

    suspend fun getCurrentWeather(lat: String, lng: String): CurrentWeatherDomain {
        return weatherDataSource.getCurrentWeather(lat,lng)
    }

    suspend fun getProvinces(): ProvincesDomain {
        return weatherDataSource.getProvinces()
    }

    suspend fun getProvinceData(id: String): ProvinceDataDomain {
        return weatherDataSource.getProvinceData(id)
    }


}