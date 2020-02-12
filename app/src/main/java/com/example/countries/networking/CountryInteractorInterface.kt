package com.example.countries.networking

import com.example.countries.repository.models.AllCountriesModel
import com.example.countries.repository.models.CountriesModel

interface CountryInteractorInterface {
    suspend fun getAllCountries(): AllCountriesModel
    suspend fun getCountryByName(name: String): CountriesModel
}