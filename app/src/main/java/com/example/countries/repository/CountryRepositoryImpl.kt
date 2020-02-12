package com.example.countries.repository

import com.example.countries.networking.CountryApiInterface
import com.example.countries.repository.models.AllCountriesModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException
import java.lang.IllegalArgumentException

class CountryRepositoryImpl : CountryRepository, KoinComponent {

    private val countriesApi: CountryApiInterface by inject()

    @Throws(IOException::class)
    override suspend fun getCountryByName(name: String): AllCountriesModel {
        return try {
            val response = countriesApi.getCountryByFullName(name)
            if (response.isSuccessful) {
                AllCountriesModel(countries = response.body()!!)
            } else {
                AllCountriesModel(error = UNEXPECTED_ERROR)
            }
        } catch (e: IllegalArgumentException) {
            AllCountriesModel(error = e.message)
        }
    }

    @Throws(IOException::class)
    override suspend fun getAllCountries(): AllCountriesModel {
        return try {
            val response = countriesApi.getAllCountries()
            if (response.isSuccessful) {
                AllCountriesModel(countries = response.body()!!)
            } else {
                AllCountriesModel(error = UNEXPECTED_ERROR)
            }
        } catch (e: IllegalArgumentException) {
            AllCountriesModel(error = e.message)
        }
    }

    companion object {
        private const val UNEXPECTED_ERROR = "Unexpected error"
    }
}