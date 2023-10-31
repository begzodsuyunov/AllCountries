package com.example.allcountries.data.source.remote.service

import com.example.allcountries.data.source.remote.dto.response.GetAllResponse
import com.excample.testdemo.data.source.remote.dto.response.GetCountry
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

    @GET("all")
    suspend fun getAll(): Response<GetAllResponse>

    @GET("name/{name}")
    suspend fun getCountry(@Path("name") name: String): Response<GetCountry>

}