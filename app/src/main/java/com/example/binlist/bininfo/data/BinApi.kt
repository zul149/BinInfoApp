package com.example.binlist.bininfo.data

import retrofit2.http.GET
import retrofit2.http.Path

// Интерфейс Retrofit API
interface BinApi {
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): BinInfo
}