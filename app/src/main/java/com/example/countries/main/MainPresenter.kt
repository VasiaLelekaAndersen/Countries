package com.example.countries.main

import androidx.fragment.app.Fragment
import com.example.countries.pager.ViewPagerFragment
import com.example.countries.main.interfaces.MainInterface
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainInterface>() {
    private val new = ViewPagerFragment()
    fun setTitle(title: String) {
        viewState.setTitle(title)
    }


    fun showStartFragment() {
        viewState.openFragment(new)
    }

    fun showFragment(fragment: Fragment) {
        viewState.openFragment(fragment)
    }

    fun showWithStackFragment(fragment: Fragment) {
        viewState.openWithStackFragment(fragment)
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