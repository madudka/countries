package com.madudka.countries.model.repository

import com.madudka.countries.model.api.API

class CountriesRepository constructor(private val api: API) {
    suspend fun getCountriesData() = api.provideCountriesAPI().getCountriesData()
}