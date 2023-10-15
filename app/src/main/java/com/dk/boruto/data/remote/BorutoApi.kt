package com.dk.boruto.data.remote

import com.dk.boruto.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BorutoApi {

    @GET("/boruto/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1,
        @Header("X-Application-ID") applicationId: String = "Mobile"
    ): ApiResponse

    @GET("/boruto/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String,
        @Header("X-Application-ID") applicationId: String = "Mobile"
    ): ApiResponse
}