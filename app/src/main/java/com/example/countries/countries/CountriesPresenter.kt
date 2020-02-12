package com.example.countries.countries

import com.example.countries.networking.CoroutineContextProvider
import com.example.countries.networking.CountryInteractorInterface
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import org.koin.core.KoinComponent


@InjectViewState
class CountriesPresenter(val countryApiInteractor: CountryInteractorInterface) :
    MvpPresenter<CountriesInterface>(), KoinComponent {

    var coroutineContextProvider = CoroutineContextProvider()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getCountriesList()
    }

    fun hideBackButton() {
        viewState.hideBackButton()
    }

    fun setToolbarTitle(title: String) {
        viewState.setToolbarTitle(title)
    }

    private fun getCountriesList() {
        GlobalScope.launch(coroutineContextProvider.io) {
            withContext(coroutineContextProvider.main) {
                viewState.showLoader()
            }
            val response = countryApiInteractor.getAllCountries()
            when {
                response.error != null -> {
                    withContext(coroutineContextProvider.main) {
                        viewState.hideLoader()
                        viewState.showToastMessage(response.error)
                    }
                }
                response.countries != null -> {
                    withContext(coroutineContextProvider.main) {
                        viewState.hideLoader()
                        viewState.showData(response.countries)
                    }
                }
            }
        }
    }
}

