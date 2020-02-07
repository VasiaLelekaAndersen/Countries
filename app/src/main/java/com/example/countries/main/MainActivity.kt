package com.example.countries.main

import android.os.Bundle
import com.example.countries.countries.CountriesFragment
import com.example.countries.adapters.FragmentAdapter
import com.example.countries.adapters.FragmentAdapterModel
import com.example.countries.R
import com.example.countries.extencions.showToast
import com.example.countries.main.interfaces.MainInterface
import com.example.countries.main.interfaces.ToolbarInterface
import com.example.countries.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_main.viewPager
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainInterface, ToolbarInterface {

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        setToolbarTitle(getString(R.string.app_name))
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
        initViewPager()
    }

    private fun initViewPager() {
        viewPager.adapter = FragmentAdapter(
            supportFragmentManager,
            listOf(
                FragmentAdapterModel(
                    CountriesFragment.newInstance(),
                    getString(R.string.country_page_title)
                ), FragmentAdapterModel(
                    SearchFragment.newInstance(),
                    getString(R.string.search_page_title)
                )
            )
        )
    }

    override fun showBackButton() {
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun setBackButtonVisible(isVisible: Boolean) {
        if (isVisible) presenter.showBackButton() else presenter.hideBackButton()
    }

    override fun setToolbarTitle(title: String) {
        presenter.setTitle(title)
    }

    override fun showMessage(message: String) {
        showToast(message)
    }

    override fun hideBackButton() {
        supportActionBar!!.setDisplayShowHomeEnabled(false)
    }

    override fun setTitle(title: String) {
        supportActionBar!!.title = title
    }
}