package com.visma.technical.data.source

import com.visma.technical.data.remote.LocalWeatherService
import com.visma.technical.data.remote.ProvincesService

import com.visma.technical.domain.model.CurrentWeatherDomain
import com.visma.technical.domain.model.ProvinceDataDomain
import com.visma.technical.domain.model.ProvincesDomain
import com.visma.technical.domain.toDomain
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VismaServerDataSource @Inject constructor(
    private val localWeatherService: LocalWeatherService,
    private val provincesService: ProvincesService
) : RemoteDataSource {
    override suspend fun getCurrentWeather(lat: String, lng: String): CurrentWeatherDomain =

        try {

            val response = localWeatherService.currentWeather("6362946b51f4455b9f6184747212205",lat +"," +lng,"json")

            response.body()?.toDomain()!!

        } catch (exception: Exception){
            CurrentWeatherDomain("")
        }

    override suspend fun getProvinces(): ProvincesDomain =

        try {

            val response = provincesService.provinces()

            response.body()?.toDomain()!!

        } catch (exception: Exception) {
            ProvincesDomain(listOf())
        }

    override suspend fun getProvinceData(): ProvinceDataDomain =

        try {

            val response = provincesService.province("v")

            response.body()?.toDomain()!!

        } catch (exception: Exception){
            ProvinceDataDomain("", listOf())
        }

}