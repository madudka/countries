package com.madudka.countries.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {
    private val api: Retrofit by lazy { initApi() }

    private fun initApi() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://restcountries.com/v2/")
        .build()

    fun provideCountriesAPI() : CountriesAPI = api.create(CountriesAPI::class.java)
}