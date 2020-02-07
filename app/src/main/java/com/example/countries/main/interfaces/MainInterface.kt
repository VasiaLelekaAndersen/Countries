package com.example.countries.main.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainInterface : MvpView {
    fun setTitle(title: String)

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = BACK_BUTTON_TAG)
    fun showBackButton()

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = BACK_BUTTON_TAG)
    fun hideBackButton()

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: String)
}

private const val BACK_BUTTON_TAG = "BACK_BUTTON_TAG"