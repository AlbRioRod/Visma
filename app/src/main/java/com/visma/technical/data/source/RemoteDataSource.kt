package com.visma.technical.data.source

import com.visma.technical.data.remote.dto.ProvinceDataResponse
import com.visma.technical.data.remote.dto.Provinces
import com.visma.technical.domain.model.CurrentWeatherDomain
import com.visma.technical.domain.model.ProvinceDataDomain
import com.visma.technical.domain.model.ProvincesDomain

interface RemoteDataSource {

    suspend fun getCurrentWeather(lat: String, lng: String): CurrentWeatherDomain
    suspend fun getProvinces(): ProvincesDomain
    suspend fun getProvinceData(id: String): ProvinceDataDomain
}