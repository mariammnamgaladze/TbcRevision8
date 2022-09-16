package com.example.tbcrevision8.data.network

import com.example.tbcrevision8.data.model.Random
import retrofit2.Response
import retrofit2.http.GET

interface RandomApi {

    @GET ("v3/05d71804-4628-4269-ac03-f86e9960a0bb")
    suspend fun getRandoms():Response<List<Random>>
}
