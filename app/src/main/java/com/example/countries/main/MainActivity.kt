package com.example.countries.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.countries.R
import com.example.countries.pager.ViewPagerFragment
import com.example.countries.extencions.showToast
import com.example.countries.main.interfaces.FragmentMangerInterface
import com.example.countries.main.interfaces.MainInterface
import com.example.countries.main.interfaces.ToolbarInterface
import kotlinx.android.synthetic.main.activity_main.toolbar
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainInterface, ToolbarInterface,
    FragmentMangerInterface {


    override fun openSingleFragment(fragment: Fragment) {
        presenter.showFragment(fragment)
    }

    override fun openAndAddStackFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.container, fragment)
            .commit()
    }


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

        if (savedInstanceState == null) {
            openFragment(ViewPagerFragment())
        } else {
            openFragment(supportFragmentManager.findFragmentById(R.id.container)!!)
        }
    }

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }


    override fun openWithStackFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.container, fragment)
            .commit()
    }


    override fun showBackButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
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
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun setTitle(title: String) {
        supportActionBar!!.title = title
    }

}