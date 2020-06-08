package com.example.maskinfokotlin.repository

import com.example.maskinfokotlin.model.StoreInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface MaskService {
    @GET("storesByGeo/json/?m=5000")
    suspend fun fetchStoreInfo(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double
    ): StoreInfo

    companion object {
        const val BASE_URL = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/"
    }
}
