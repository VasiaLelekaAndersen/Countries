package com.example.countries.repository.models

import com.example.countries.networking.models.Country

data class CountriesModel(val country: Country? = null, val error: String? = null)