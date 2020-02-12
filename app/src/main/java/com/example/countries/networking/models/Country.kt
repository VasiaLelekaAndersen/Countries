package com.example.countries.networking.models

data class Country(
    val name: String,
    val topLevelDomain: List<String>,
    val alpha2Code: String,
    val alpha3Code: String,
    val callingCodes: List<String>,
    val capital: String,
    val altSpellings: List<String>,
    val region: String,
    val subregion: String,
    val population: Int,
    val latlang: List<Int>,
    val demonym: String,
    val area: Float,
    val gini: Float,
    val timezones: List<String>,
    val borders: List<String>?,
    val nativeName: String,
    val numericCode: String,
    val currencies: List<Currencies>,
    val languages: List<Language>,
    val translations: Translations,
    val flag: String,
    val regionalBlocs: List<RegionalBlocs>,
    val cioc: String
)