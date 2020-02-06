package com.example.countries.main.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainInterface : MvpView {
    fun setTitle(title: String)

    fun showBackButton()

    fun hideBackButton()

    fun showMessage(message : String)
}