package com.visma.technical.domain.usecase

import com.visma.technical.data.repository.WeatherRepository
import com.visma.technical.domain.model.CurrentWeatherDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentWeather @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun invoke(lat: String, lng: String): CurrentWeatherDomain =

        weatherRepository.getCurrentWeather(lat, lng)

}