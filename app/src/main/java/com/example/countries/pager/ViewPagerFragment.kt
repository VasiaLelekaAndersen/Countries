package com.example.countries.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.countries.R
import com.example.countries.adapters.FragmentAdapter
import com.example.countries.adapters.FragmentAdapterModel
import com.example.countries.countries.CountriesFragment
import com.example.countries.search.SearchFragment
import kotlinx.android.synthetic.main.fragment_view_pager.viewPager
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ViewPagerFragment : MvpAppCompatFragment(), ViewPagerInterface {

    @InjectPresenter
    internal lateinit var presenter: ViewPagerPresenter

    private val adapter by lazy {
        FragmentAdapter(
            childFragmentManager,
            mutableListOf(
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

    override fun setAdapter() {
        viewPager.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_view_pager, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // presenter.setViewAdapter()
        setAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
