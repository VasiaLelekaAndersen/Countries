package com.example.countries.repository.models

import com.example.countries.networking.models.Country

data class AllCountriesModel(
    val countries: List<Country>? = null,
    val error: String? = null
)