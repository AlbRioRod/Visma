package com.visma.technical.domain

import com.visma.technical.data.remote.dto.*
import com.visma.technical.domain.model.*

fun Provinces.toDomain() = ProvincesDomain(

    provinces = this.provinceResponses.map { it.toDomain() }

)

fun ProvinceResponse.toDomain() = ProvinceDomain(
    this.code,
    this.name
)

fun CurrentWeatherResponse.toDomain() = CurrentWeatherDomain(
    this.data.currentCondition.first().temp
)


fun ProvinceDataResponse.toDomain() = ProvinceDataDomain(
    this.description,
    this.ciudades.map { it.toDomain() }
)

fun CityResponse.toDomain() = City(
    this.name,
    this.temperatures.toDomain()
)

fun TemperatureResponse.toDomain() = Temperature(
    this.max,
    this.min
)