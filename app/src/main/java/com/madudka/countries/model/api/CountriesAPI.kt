package com.madudka.countries.model.api

import com.madudka.countries.model.CountryModel
import retrofit2.Response
import retrofit2.http.GET

interface CountriesAPI {
    @GET("all")
    suspend fun getCountriesData() : Response<List<CountryModel>>
}