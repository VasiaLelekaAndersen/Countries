package com.example.countries.countries

import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class CountriesPresenter : MvpPresenter<CountriesInterface>(){
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }
}