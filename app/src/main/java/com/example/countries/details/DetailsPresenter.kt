package com.example.countries.details

import com.example.countries.networking.CoroutineContextProvider
import com.example.countries.networking.CountryInteractorInterface
import com.example.countries.networking.models.Language
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import org.koin.core.KoinComponent

@InjectViewState
class DetailsPresenter(
    val countryApiInteractor: CountryInteractorInterface,
    val countryName: String
) : MvpPresenter<DetailsInterface>(), KoinComponent {

    var coroutineContextProvider = CoroutineContextProvider()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showBackButton()
        loadCountryData(countryName)
    }

    fun setTitle(title: String) {
        viewState.setTitle(title)
    }

    fun hideBackButton() {
        viewState.hideBackButton()
    }

    fun showBackButton() {
        viewState.showBackButton()
    }

    fun showExtraLanguage(show: Boolean) {
        if (show) viewState.showAllLanguages() else viewState.hideAllLanguages()
    }

    fun showAllLanguagesMark(languages: List<Language>) {
        if (languages.size > 1) {
            viewState.showAllLanguagesMark()
            viewState.fullLanguages(languages.drop(1))
        }
    }


    fun loadCountryData(name: String) {
        viewState.showProgressBar()
        GlobalScope.launch(coroutineContextProvider.io) {
            val response = countryApiInteractor.getCountryByName(name)
            when {
                response.country != null -> {
                    withContext(coroutineContextProvider.main) {
                        viewState.hideProgressBar()
                        viewState.showCountryData(response.country)
                    }
                }
                response.error != null -> {
                    withContext(coroutineContextProvider.main) {
                        viewState.hideProgressBar()
                        viewState.showToastMessage(response.error)
                    }
                }
            }
        }
    }
}