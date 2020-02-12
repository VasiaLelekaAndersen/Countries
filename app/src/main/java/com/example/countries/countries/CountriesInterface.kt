package com.example.countries.countries

import com.example.countries.networking.models.Country
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CountriesInterface : MvpView {

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = LOADER_TAG)
    fun showLoader()

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = LOADER_TAG)
    fun hideLoader()


    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = BACK_BUTTON_TAG)
    fun showBackButton()


    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = BACK_BUTTON_TAG)
    fun hideBackButton()


    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = LOADER_TAG)
    fun showData(countries: List<Country>)

    fun setToolbarTitle(title: String)

    fun showToastMessage(error: String)
}

private const val LOADER_TAG = "LOADER_TAG"
private const val BACK_BUTTON_TAG = "BACK_BUTTON_TAG"