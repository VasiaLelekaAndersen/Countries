package com.example.countries.main

import com.example.countries.main.interfaces.MainInterface
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainInterface>() {
    fun setTitle(title: String) {
        viewState.setTitle(title)
    }

    fun showBackButton() {
        viewState.showBackButton()
    }

    fun hideBackButton() {
        viewState.hideBackButton()
    }

    fun showToast(message: String) {
        viewState.showMessage(message)
    }
}