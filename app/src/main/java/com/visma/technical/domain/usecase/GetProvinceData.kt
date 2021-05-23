package com.visma.technical.domain.usecase

import com.visma.technical.data.repository.WeatherRepository
import com.visma.technical.domain.model.CurrentWeatherDomain
import com.visma.technical.domain.model.ProvinceDataDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetProvinceData @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun invoke(id: String): ProvinceDataDomain =

        weatherRepository.getProvinceData(id)

}