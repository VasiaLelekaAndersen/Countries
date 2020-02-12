package com.example.countries.main.interfaces

import androidx.fragment.app.Fragment

interface FragmentMangerInterface {

    fun openSingleFragment(fragment: Fragment)

    fun openAndAddStackFragment(fragment: Fragment)
}