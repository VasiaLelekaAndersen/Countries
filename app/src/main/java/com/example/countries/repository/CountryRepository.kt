package com.example.countries.repository

import com.example.countries.repository.models.AllCountriesModel

interface CountryRepository {
    suspend fun getAllCountries(): AllCountriesModel

    suspend fun getCountryByName(name : String): AllCountriesModel
}