package com.madudka.countries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.madudka.countries.model.CountryModel
import com.madudka.countries.model.api.API
import com.madudka.countries.model.repository.CountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class CountriesViewModel : ViewModelExceptionHandle() {

    private val countriesRepository = CountriesRepository(API())

    private val countries: MutableLiveData<List<CountryModel>> by lazy { MutableLiveData<List<CountryModel>>() }

    fun getCountries(): LiveData<List<CountryModel>> = countries
    fun getCountryByPosition(position: Int): CountryModel? = countries.value?.get(position)

    init {
        refreshData()
    }

    fun refreshData(){
        viewModelScope.launch(exceptionHandler) {
            val fetchResult = fetchCountries()
            if (fetchResult.isSuccessful) countries.postValue(fetchResult.body())
            else error.postValue("Error code: ${fetchResult.code()}")
        }
    }

    private suspend fun fetchCountries(): Response<List<CountryModel>> {
        return withContext(Dispatchers.IO + exceptionHandler){
            countriesRepository.getCountriesData()
        }
    }
}
