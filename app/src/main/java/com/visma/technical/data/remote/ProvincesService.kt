package com.visma.technical.data.remote

import com.visma.technical.data.remote.dto.ProvinceDataResponse
import com.visma.technical.data.remote.dto.Provinces
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProvincesService {

    @GET("provincias")
    suspend fun provinces(): Response<Provinces>

    @GET("provincias/{code}")
    suspend fun province(@Path(value = "code", encoded = true) code: String): Response<ProvinceDataResponse>
}