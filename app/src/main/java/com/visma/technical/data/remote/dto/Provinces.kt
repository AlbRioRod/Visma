package com.visma.technical.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Provinces(

    @Expose
    @SerializedName("provincias")
    val provinceResponses: List<ProvinceResponse>
)


data class ProvinceResponse(
    @Expose
    @SerializedName("CODPROV")
    val code: String,

    @Expose
    @SerializedName("NOMBRE_PROVINCIA")
    val name: String
)