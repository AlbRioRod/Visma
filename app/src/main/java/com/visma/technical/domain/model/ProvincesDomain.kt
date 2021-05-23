package com.visma.technical.domain.model

data class ProvincesDomain(
    var provinces: List<ProvinceDomain>
)

data class ProvinceDomain(
    val id: String,
    var name: String
)