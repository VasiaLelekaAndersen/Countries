package com.example.countries.networking

import com.example.countries.repository.CountryRepository
import com.example.countries.repository.models.AllCountriesModel
import com.example.countries.repository.models.CountriesModel
import java.io.IOException

class CountryApiInteractor(private val countryRepository: CountryRepository) :
    CountryInteractorInterface {

    override suspend fun getCountryByName(name: String): CountriesModel {
        return try {
            val response = countryRepository.getCountryByName(name)
            when {
                response.countries != null -> CountriesModel(response.countries.first())
                response.error != null -> CountriesModel(error = response.error)
                else -> CountriesModel()
            }
        } catch (e: IOException) {
            CountriesModel(error = e.message)
        }
    }

    override suspend fun getAllCountries(): AllCountriesModel {
        return try {
            countryRepository.getAllCountries()
        } catch (e: IOException) {
            AllCountriesModel(error = e.message)

        }
    }
}