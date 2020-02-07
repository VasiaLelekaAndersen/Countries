package com.example.countries.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.countries.R
import moxy.MvpAppCompatFragment


class CountriesFragment : MvpAppCompatFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_countries, container, false)

    companion object {
        fun newInstance(): CountriesFragment {
            return CountriesFragment()
        }
    }
}
