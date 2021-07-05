package com.edanuryildirim.futbolapi.service

import com.edanuryildirim.futbolapi.model.FutbolModel

import retrofit2.Response
import retrofit2.http.GET


interface API {

    @GET("edanurryldr/SoccerLeauge/main/futbol.json")
    suspend fun getData(): Response<List<FutbolModel>>
}