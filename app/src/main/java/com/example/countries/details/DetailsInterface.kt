package com.example.countries.details

import com.example.countries.networking.models.Country
import com.example.countries.networking.models.Language
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailsInterface : MvpView {

    fun showBackButton()

    fun hideBackButton()

    fun setTitle(title: String)

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = DETAILS_LOADER_TAG)
    fun showCountryData(country: Country)

    fun fullLanguages(languages : List<Language>)

    fun showAllLanguagesMark()

    fun showToastMessage(message: String)

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = ALL_LANGUAGES_TAG)
    fun showAllLanguages()

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = ALL_LANGUAGES_TAG)
    fun hideAllLanguages()

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = DETAILS_LOADER_TAG)
    fun showProgressBar()

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = DETAILS_LOADER_TAG)
    fun hideProgressBar()

}

private const val DETAILS_LOADER_TAG = "DETAILS_LOADER_TAG"
private const val ALL_LANGUAGES_TAG = "ALL_LANGUAGES_TAG"