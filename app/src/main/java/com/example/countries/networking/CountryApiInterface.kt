package com.example.countries.networking

import com.example.countries.networking.models.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApiInterface {

    @GET("rest/v2/all")
    suspend fun getAllCountries(): Response<List<Country>>

    @GET("rest/v2/name/{name}?fullText=true")
    suspend fun getCountryByFullName(@Path("name") name: String): Response<List<Country>>
}