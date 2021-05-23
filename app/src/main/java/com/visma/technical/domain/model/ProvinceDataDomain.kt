package com.visma.technical.domain.model

data class ProvinceDataDomain(
    var description: String,
    var cities: List<City>
)

data class City(

    var name: String,
    var temperature: Temperature
)

data class Temperature(

    var max: String,
    var min: String
)