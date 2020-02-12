package com.example.countries.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.get
import com.example.countries.R
import com.example.countries.adapters.PaddingItemDecoration
import com.example.countries.adapters.LanguageAdapter
import com.example.countries.extencions.setUrlImage
import com.example.countries.extencions.showToast
import com.example.countries.main.interfaces.ToolbarInterface
import com.example.countries.networking.models.Country
import com.example.countries.networking.models.Language
import kotlinx.android.synthetic.main.fragment_details.capital
import kotlinx.android.synthetic.main.fragment_details.flag
import kotlinx.android.synthetic.main.fragment_details.language
import kotlinx.android.synthetic.main.fragment_details.languageMark
import kotlinx.android.synthetic.main.fragment_details.languageRecView
import kotlinx.android.synthetic.main.fragment_details.name
import kotlinx.android.synthetic.main.fragment_details.progressBar
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class DetailsFragment : MvpAppCompatFragment(), DetailsInterface {

    @InjectPresenter
    internal lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter() = DetailsPresenter(get(), arguments!!.getString(DETAILS_EXTRA)!!)

    private val toolbarEvents by lazy { context as ToolbarInterface }

    private val adapter by lazy { LanguageAdapter(emptyList()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setTitle(getString(R.string.country_details_fragment_title))
        presenter.showBackButton()
        languageRecView.layoutManager = LinearLayoutManager(context!!)
        languageRecView.adapter = adapter
        languageRecView.addItemDecoration(PaddingItemDecoration(4, 2))
        languageMark.setOnCheckedChangeListener { _, isChecked ->
            presenter.showExtraLanguage(
                isChecked
            )
        }
    }

    override fun showCountryData(country: Country) {
        name.text = country.name
        flag.setUrlImage(country.flag)
        capital.text = country.capital
        language.text = country.languages.first().name
        presenter.showAllLanguagesMark(country.languages)
    }

    override fun hideBackButton() {
        toolbarEvents.setBackButtonVisible(false)
    }

    override fun showAllLanguagesMark() {
        languageMark.visibility = View.VISIBLE

    }

    override fun showToastMessage(message: String) {
        context!!.showToast(message)
    }

    override fun showAllLanguages() {
        languageRecView.visibility = View.VISIBLE
    }

    override fun hideAllLanguages() {
        languageRecView.visibility = View.GONE
    }

    override fun showBackButton() {
        toolbarEvents.setBackButtonVisible(true)
    }

    override fun setTitle(title: String) {
        toolbarEvents.setToolbarTitle(title)
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun fullLanguages(languages: List<Language>) {
        adapter.updateList(languages)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }


    companion object {
        fun newInstance(country: String): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        DETAILS_EXTRA,
                        country
                    )
                }
            }
        }

        private const val DETAILS_EXTRA = "DETAILS_EXTRA"
    }
}
