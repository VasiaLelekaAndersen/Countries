package com.example.countries.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.countries.R
import moxy.MvpAppCompatFragment

class SearchFragment : MvpAppCompatFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)


    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}