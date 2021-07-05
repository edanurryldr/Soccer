package com.edanuryildirim.futbolapi.repository

import com.edanuryildirim.futbolapi.service.RetrofitInstance

class AppRepository {
    suspend fun getPictures() = RetrofitInstance.footballApi.getData()
}