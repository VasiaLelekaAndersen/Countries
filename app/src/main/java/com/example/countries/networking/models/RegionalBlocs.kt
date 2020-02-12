package com.example.countries.networking.models

data class RegionalBlocs(
    val acronym: String,
    val name: String,
    val otherAcronyms: List<String>? = null,
    val otherNames: List<String>? = null
)