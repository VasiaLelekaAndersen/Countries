package com.example.countries.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.R
import com.example.countries.adapters.CountriesAdapter
import com.example.countries.adapters.PaddingItemDecoration
import com.example.countries.details.DetailsFragment
import com.example.countries.extencions.showToast
import com.example.countries.main.interfaces.FragmentMangerInterface
import com.example.countries.main.interfaces.ToolbarInterface
import com.example.countries.networking.models.Country
import kotlinx.android.synthetic.main.fragment_countries.countriesRecView
import kotlinx.android.synthetic.main.fragment_countries.progressBar
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get


class CountriesFragment : MvpAppCompatFragment(), CountriesInterface {
    @InjectPresenter
    internal lateinit var presenter: CountriesPresenter

    @ProvidePresenter
    fun createPresenter() = CountriesPresenter(get())

    private val toolbarEvents by lazy { context as ToolbarInterface }

    private val countryAdapter by lazy {
        CountriesAdapter(emptyList()) {
            (context as FragmentMangerInterface).openAndAddStackFragment(
                DetailsFragment.newInstance(
                    it
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_countries, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countriesRecView.layoutManager = LinearLayoutManager(context!!)
        countriesRecView.adapter = countryAdapter
        countriesRecView.addItemDecoration(
            PaddingItemDecoration(
                resources.getDimensionPixelSize(R.dimen.sidePadding),
                resources.getDimensionPixelSize(R.dimen.topPadding)
            )
        )
    }

    override fun onResume() {
        super.onResume()
        presenter.setToolbarTitle(getString(R.string.app_name))
        presenter.hideBackButton()
    }

    override fun showToastMessage(error: String) {
        context!!.showToast(error)
    }

    override fun setToolbarTitle(title: String) {
        toolbarEvents.setToolbarTitle(title)
    }

    override fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    override fun showData(countries: List<Country>) {
        countryAdapter.updateList(countries)
    }

    override fun showBackButton() {
        toolbarEvents.setBackButtonVisible(true)
    }

    override fun hideBackButton() {
        toolbarEvents.setBackButtonVisible(false)
    }

    companion object {
        fun newInstance(): CountriesFragment {
            return CountriesFragment()
        }
    }
}
